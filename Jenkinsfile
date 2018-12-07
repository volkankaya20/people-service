def project = 'peopel-service'
def  appName = 'people-rest-service'
def tenancy='gse00013828'
def  imageTag = "iad.ocir.io/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline { 
	  agent {
    kubernetes {
      label 'people-service-app'
      defaultContainer 'jnlp'
      yaml """
apiVersion: v1
kind: Pod
metadata:
labels:
  component: ci
spec:
  # Use service account that can deploy to all namespaces
  serviceAccountName: cd-jenkins
  containers:
  - name: gradle
    image: gradle:4.6
    command:
    - cat
    tty: true
  - name: docker
    image: docker
    command:
    - cat
    tty: true
  - name: kubectl
    image: allokubs/kubectl
    command:
    - cat
    tty: true
"""
}
  }
	stages {
		stage('Build Stage'){			
			steps {		
				container('gradle') {		
		    		sh 'gradle -v'	
	    		}	
				
			}			
		}
		stage('Build Image '){			
			steps {		
				container('docker') {		
		    		sh 'docker -v'	
	    		}	
				
			}			
		}
		stage('Deploy To Kubernetes'){
			environment { 
                KUBECONFIG = credentials('oci-kubernetes') 
            }
            options {
			    withKubeConfig(caCertificate: '', contextName: '', credentialsId: 'oci-kubernetes', serverUrl: '')
			}
			steps {		
				container('kubectl') {		
		    		sh 'kubectl get pods'	
	    		}	
				
			}			
		}
		
	}
}
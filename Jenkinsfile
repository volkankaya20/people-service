def project = 'people-service'
def appName = 'people-rest-service'
def tenancy='gse00013828'
def imageTag = "iad.ocir.io/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

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
    image: gradle:5.0.0-jdk8    
    command:
    - cat
    tty: true
    securityContext:
       runAsUser: 10000
       allowPrivilegeEscalation: false
  - name: docker
    image: docker
    volumeMounts:
    - mountPath: /var/run/docker.sock
      name: docker-socket-volume
    securityContext:
      privileged: true
	volumes:
	  - name: docker-socket-volume
	    hostPath:
	      path: /var/run/docker.sock
	      type: File
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
		stage('Build Application'){			
			steps {		
				container('gradle') {		
		    		sh 'gradle clean build'	
	    		}	
				
			}			
		}	
		stage('Build Image and push'){			
			steps {		
				container('docker') {		
		    		withDockerRegistry(credentialsId: 'ocir-credentials', url: 'https://iad.ocir.io') {
					      sh """
							docker -v			           
				            docker build -t ${imageTag} .
				            docker push ${imageTag}
				            """
					}	
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
		    		sh("sed -i.bak 's#iad.ocir.io/gse00013828/oracleimc/people-rest-service:1.0#${imageTag}#' ./people-service-deployment.yaml")
		    		sh("kubectl apply -f people-service-deployment.yaml")
	    		}	
				
			}			
		}		
		
	}
}
def project = 'people-service'
def appName = 'volkan-people-service'
def tenancy='frxplqvlwvmz'
def ocir='fra.ocir.io'
def imageTag = "${ocir}/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline { 
	  agent {
    kubernetes {
      label 'people-service-app-build'
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
  - name: kubectl
    image: allokubs/kubectl
    command:
    - cat
    tty: true
  - name: gradle
    image: gradle:5.3.1-jdk8    
    command:
    - cat
    tty: true
    securityContext:
       runAsUser: 1000
       allowPrivilegeEscalation: false
    env:
    - name: DOCKER_HOST
      value: tcp://localhost:2375
  - name: docker
    image: docker:18.05-dind
    securityContext:
      privileged: true
    volumeMounts:
      - name: dind-storage
        mountPath: /var/lib/docker
  volumes:
  - name: dind-storage
    emptyDir: {}
    command:
    - cat
    tty: true
"""
}
  }
  triggers {
        pollSCM ('* * * * *')
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
		    		withDockerRegistry(credentialsId: 'ocir-credentials', url: "https://${ocir}") {
					      sh """				           
				            docker build -t ${imageTag} .
				            docker push ${imageTag}
				            """
					}	
	    		}	
				
			}			
		}
		stage('Deploy To Kubernetes'){
		
			steps {		
				container('kubectl') {		
		    		sh 'kubectl get pods'	
		    		sh("sed -i.bak 's#iad.ocir.io/gse00013828/oracleimc/people-rest-service:1.0#${imageTag}#' ./k8s/deployments/people-service-deployment.yaml")
		    		sh("kubectl apply -f ./k8s/deployments/people-service-deployment.yaml")
		    		sh("kubectl apply -f ./k8s/services/people-service.yaml")
		    		sh("kubectl apply -f ./k8s/services/ingress.yaml")
		    		sh("echo `kubectl get svc -o jsonpath='{.items[*].status.loadBalancer.ingress[*].ip}' --all-namespaces`")            
	    		}	
				
			}			
		}		
		
	}
}
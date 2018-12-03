def project = 'peopel-service'
def  appName = 'people-rest-service'
def tenancy='gse00013828'
def  imageTag = "iad.ocir.io/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline { 
	agent any
	stages {
		stage('Kubectl'){
			agent {
				kubernetes
			}			
			steps {
				sh 'kubectl get pods'
			}			
		}
	}
}
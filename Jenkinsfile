def project = 'peopel-service'
def  appName = 'people-rest-service'
def tenancy='gse00013828'
def  imageTag = "iad.ocir.io/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

pipeline { 
	agent any
	stages {
		stage('Kubectl'){
			environment { 
                KUBECONFIG = credentials('oci-kubernetes') 
            }
            options {
			    withKubeConfig(caCertificate: '', contextName: '', credentialsId: 'oci-kubernetes', serverUrl: '')
			}
			steps {				
		    		sh 'oci compute instance list --compartment-id=ocid1.compartment.oc1..aaaaaaaabjxwuta3abd5pv7h4cogtco4hbkzsc6b2rgne6ui4ebdk4qxk2kq'		
				
			}			
		}
	}
}
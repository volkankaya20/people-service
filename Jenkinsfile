def project = 'peopel-service'
def  appName = 'people-rest-service'
def tenancy='gse00013828'
def  imageTag = "iad.ocir.io/${tenancy}/oracleimc/${appName}:${env.BRANCH_NAME}.${env.BUILD_NUMBER}"

node {
  stage('List pods') {
    withKubeConfig([credentialsId: 'oci-kubernetes',
                    caCertificate: '',
                    serverUrl: '',
                    contextName: ''
                    ]) {
      sh 'kubectl get pods'
    }
  }
}
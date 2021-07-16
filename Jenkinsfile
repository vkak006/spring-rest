node {
        withEnv(['AZURE_SUBSCRIPTION_ID=',
        'AZURE_TENANT_ID=']) {

            stage('init') {
                checkout scm
            }

            stage('build') {
              sh 'mvn clean package'
            }
        }
}
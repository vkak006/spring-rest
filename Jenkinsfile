node {
        withEnv(['AZURE_SUBSCRIPTION_ID=61c9ef13-e529-4145-bcb5-7251b7dbdbcd',
        'AZURE_TENANT_ID=98a4a14e-de33-4594-9f0b-71479427cf55']) {

            stage('init') {
                checkout scm
            }

            stage('build') {
              sh 'mvn clean package'
            }
        }
}
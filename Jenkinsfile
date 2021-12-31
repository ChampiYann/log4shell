// Build a Maven project using the standard image and Scripted syntax.
// Rather than inline YAML, you could use: yaml: readTrusted('jenkins-pod.yaml')
// Or, to avoid YAML: containers: [containerTemplate(name: 'maven', image: '3.8.4-openjdk-11', command: 'sleep', args: 'infinity')]
podTemplate(containers: [containerTemplate(name: 'maven', image: 'maven:3.8.4-openjdk-11', command: 'sleep', args: 'infinity')]) {
    node(POD_LABEL) {
        stage('Get source'){
          	git branch: 'main', credentialsId: '4dd7ab60-4fa9-4195-9c1c-3285a398e5b7', url: 'http://bitbucket-release/scm/tes/log4shell.git'
        }
      	stage('SonarQube Analysis') {
            container('maven') {
                withSonarQubeEnv() {
                    configFileProvider([configFile(fileId: 'f6338d51-e080-4dc1-be7d-9ccf1459f8d7', variable: 'MAVEN_SETTINGS')]) {
                      	sh 'mvn clean verify sonar:sonar -s $MAVEN_SETTINGS -Dsonar.projectKey=TES_log4shell'
                  	}
                }
            }
        }
        stage('Deploy a log4shell project') {
            //git branch: 'main', credentialsId: '4dd7ab60-4fa9-4195-9c1c-3285a398e5b7', url: 'http://bitbucket-release/scm/tes/log4shell.git'
            container('maven') {
                configFileProvider([configFile(fileId: 'f6338d51-e080-4dc1-be7d-9ccf1459f8d7', variable: 'MAVEN_SETTINGS')]) {
                    sh 'mvn -s $MAVEN_SETTINGS clean deploy'
                }
            }
        }
    }
}

pipeline {
  agent {
     node { 
        label ''
        customWorkspace "C:\Users\ryleo\OneDrive\Documents\devop\workspacee"
        } 
  }
    tools {
    maven 'maven 3.8.2'
    jdk 'JDK 1.8'
  }
  stages {
    
    stage('Maven Clone') {
    
      steps {
       git  branch: 'master', url:'https://github.com/ruiyangg/devopsproj.git'
      }
    }
    
    
     stage('Maven Clean') {

      steps {
        bat 'mvn clean install test'
      }
    }
   
}
  post {
    success {
        deploy adapters: [tomcat9(url: 'http://localhost:8090', 
                              credentialsId: '74daef0d-88dc-490f-8892-8d3f678153d4')], 
                     war: '*/.war',
                     contextPath: 'devopsproj',onFailure:false

         }

  }
  triggers {
    pollSCM('H * * * *')
  }
}

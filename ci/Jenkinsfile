pipeline {
    agent any
    
    environment {
        PROJECT_NAME = "OpenCartAutomation"
        WORKSPACE_DIR = "${WORKSPACE}"
    }
    
    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '5'))
        timeout(time: 60, unit: 'MINUTES')
        timestamps()
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo "========== Checking out code =========="
                checkout scm
                sh 'git --version'
            }
        }
        
        stage('Build') {
            steps {
                echo "========== Building Project =========="
                sh 'mvn clean compile -DskipTests'
            }
        }
        
        stage('Test') {
            steps {
                echo "========== Running Tests =========="
                sh 'mvn test -Dsuites=master.xml'
            }
        }
        
        stage('Cross-Browser Test') {
            when {
                expression { env.RUN_CROSS_BROWSER == 'true' }
            }
            steps {
                echo "========== Running Cross-Browser Tests =========="
                sh 'mvn test -Dsuites=crossbrowsertesting.xml'
            }
        }
    }
    
    post {
        always {
            echo "========== Publishing Test Results =========="
            
            // Publish TestNG results
            junit testResults: '**/target/surefire-reports/testng-results.xml', 
                  allowEmptyResults: true
            
            // Archive test reports
            archiveArtifacts artifacts: 'reports/*.html', 
                             allowEmptyArchive: true
            
            archiveArtifacts artifacts: 'target/surefire-reports/**', 
                             allowEmptyArchive: true
            
            // Publish HTML Report
            publishHTML target: [
                reportDir: 'reports',
                reportFiles: 'Test_Report*.html',
                reportName: 'OpenCart Test Report',
                allowMissing: true,
                alwaysLinkToLastBuild: true
            ]
        }
        
        success {
            echo "========== BUILD SUCCESSFUL =========="
            echo "Tests completed successfully!"
        }
        
        failure {
            echo "========== BUILD FAILED =========="
            echo "Test execution failed. Check logs for details."
        }
        
        unstable {
            echo "========== BUILD UNSTABLE =========="
            echo "Some tests failed. Review test reports."
        }
    }
}

pipeline {
    agent any
    environment {
        dubaidash_image = ''
        consumerdashapi_image = ''
        react_image = ''
        registryUri = 'http://192.168.160.48:5000'
    }
    tools {
        jdk 'jdk11'
    }
    stages {
        
        stage('Cloning repository') {
            steps {
                git(
                    branch: 'master',
                    url: 'https://github.com/DubaiDash-ES/code.git'
                )
                sh "chmod +x -R ${env.WORKSPACE}"
            }
        }
        /*
        stage('Testing'){
            steps{
                sh './jenkins/scripts/tests.sh'
            }
        }*/
        
        stage('Build') {
            
            steps {
                sh './jenkins/scripts/build.sh'
            }
        }
        
        stage('Build and deploy images to registry') {
            
            steps {
                script {
                    dubaidash_image = docker.build("esp20_dubaidash","./dubaidash") 
                    docker.withRegistry(registryUri) {
                        dubaidash_image.push("$BUILD_NUMBER")
                        dubaidash_image.push('latest')
                    }
                    
                    consumerdashapi_image = docker.build("esp20_dubaidashapi","./dubaidashApi")
                    docker.withRegistry(registryUri) {
                        consumerdashapi_image.push("$BUILD_NUMBER")
                        consumerdashapi_image.push('latest')
                    }
                    
                    react_image = docker.build("esp20_react","./dubaidash-react-app")
                    docker.withRegistry(registryUri) {
                        react_image.push("$BUILD_NUMBER")
                        react_image.push('latest')
                    }
                }
            }
        }
        
        stage('Remote SSH') {
            steps{
                sshagent (credentials: ['esp20_playground_ssh']) {
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS'){
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker stop esp20_dubaidash' 
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rm esp20_dubaidash'
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rmi  192.168.160.48:5000/esp20_dubaidash'
                    }
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS'){
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker stop esp20_dubaidashapi'  
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rm esp20_dubaidashapi'
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rmi  192.168.160.48:5000/esp20_dubaidashapi'
                    }
                    catchError(buildResult: 'SUCCESS', stageResult: 'SUCCESS'){
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker stop esp20_react'  
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rm esp20_react'
                        sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker rmi  192.168.160.48:5000/esp20_react'
                    }
                    //sh 'ssh -o StrictHostKeyChecking=no esp20@192.168.160.87 docker-compose up -d esp20_dubaidash esp20_dubaidashapi esp20_react'
                }
            }
        }
    }
}
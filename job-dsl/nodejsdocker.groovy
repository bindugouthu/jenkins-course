job('NodeJS Docker example') {
    scm {
        git('git://github.com/bindugouthu/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('bindugouthu')
            node / gitConfigEmail('bindugouthu@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('gowthuhi/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

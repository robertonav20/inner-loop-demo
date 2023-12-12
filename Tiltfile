# version_settings() enforces a minimum Tilt version
# https://docs.tilt.dev/api.html#api.version_settings
version_settings(constraint='>=0.22.2')

# 0-base
docker_build(
    'example-java-image',
    context='./0-base',
    dockerfile='./0-base/Dockerfile',
    only=['./api/'],
    live_update=[
        sync('./0-base/src/main'),
        run(
            'pip install -r /app/requirements.txt',
            trigger=['./api/requirements.txt']
        )
    ]
)
k8s_yaml('kubernetes.yaml')
k8s_resource('example-java', port_forwards=8000)

# 401-spring-boot-layertools
# For more on Extensions, see: https://docs.tilt.dev/extensions.html
load('ext://restart_process', 'docker_build_with_restart')

# Records the current time, then kicks off a server update.
# Normally, you would let Tilt do deploys automatically, but this
# shows you how to set up a custom workflow that measures it.
local_resource(
    'deploy',
    'python 401-spring-boot-layertools/record-start-time.py',
)

gradlew = "401-spring-boot-layertools/./gradlew"
if os.name == "nt":
  gradlew = "gradlew.bat"

local_resource(
  'example-java-compile',
  gradlew + ' bootJar && ' +
  'rm -rf build/jar-staging && ' +
  'java -Djarmode=layertools -jar 401-spring-boot-layertools/build/libs/example-0.0.1-SNAPSHOT.jar extract --destination 401-spring-boot-layertools/jar-extracted && ' +
  'rsync --delete --inplace --checksum -r 401-spring-boot-layertools/build/jar-extracted/ build/jar',
  deps=['src', 'build.gradle'],
  resource_deps = ['deploy'])

docker_build_with_restart(
  'example-java-image',
  './401-spring-boot-layertools/build/jar',
  entrypoint=['java', 'org.springframework.boot.loader.JarLauncher'],
  dockerfile='401-spring-boot-layertools/Dockerfile',
  live_update=[
    sync('401-spring-boot-layertools/build/jar/dependencies', '/app'),
    sync('401-spring-boot-layertools/build/jar/spring-boot-loader', '/app'),
    sync('401-spring-boot-layertools/build/jar/snapshot-dependencies', '/app'),
    sync('401-spring-boot-layertools/build/jar/application', '/app'),
  ],
)

k8s_yaml('kubernetes.yaml')
k8s_resource('example-java', port_forwards=8000, resource_deps=['deploy', 'example-java-compile'])
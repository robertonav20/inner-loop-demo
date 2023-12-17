cat <<EOF | ctlptl apply -f -
apiVersion: ctlptl.dev/v1alpha1
kind: Registry
name: k3d-local-registry
port: 5005
listenAddress: localhost
---
apiVersion: ctlptl.dev/v1alpha1
kind: Cluster
product: k3d
name: k3d-local-cluster
registry: k3d-local-registry
EOF
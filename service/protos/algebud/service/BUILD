load("@rules_proto_grpc//java:defs.bzl", "java_grpc_library")
load("@rules_proto_grpc//python:defs.bzl", "python_grpc_library")

proto_library(
    name = "algebud_protos",
    srcs = ["algebud.proto"],
)

proto_library(
    name = "algebud_service_protos",
    srcs = ["algebud_service.proto"],
    deps = ["algebud_protos"],
)

java_grpc_library(
    name = "algebud_service_java_grpc",
    visibility = ["//visibility:public"],
    protos = [
        ":algebud_protos",
        ":algebud_service_protos",
    ],
)

python_grpc_library(
    name = "algebud_service_python_grpc",
    visibility = ["//visibility:public"],
    protos = [
        ":algebud_protos",
        ":algebud_service_protos",
    ],
)

java_binary(
    name = "server",
    srcs = ["AlgebudServer.java"],
    main_class = "algebud.service.AlgebudServer",
    runtime_deps = [
        "@io_grpc_grpc_java//netty",
    ],
    deps = [
        "//core/java/algebud",
        "//core/java/algebud/token",
        "//service/protos/algebud/service:algebud_service_java_grpc",
        "@io_grpc_grpc_java//api",
        "@io_grpc_grpc_java//protobuf",
        "@io_grpc_grpc_java//stub",
        "@maven//:com_google_api_grpc_proto_google_common_protos",
        "@maven//:com_google_code_findbugs_jsr305",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_guava_guava",
    ]
)

java_binary(
    name = "client",
    srcs = ["AlgebudClient.java"],
    main_class = "algebud.service.AlgebudClient",
    runtime_deps = [
        "@io_grpc_grpc_java//netty",
    ],
    deps = [
        "//core/java/algebud",
        "//service/protos/algebud/service:algebud_service_java_grpc",
        "@io_grpc_grpc_java//api",
    ]
)

java_binary(
    name = "root_finder",
    srcs = ["RootFinder.java"],
    main_class = "algebud.service.RootFinder",
    runtime_deps = [
        "@io_grpc_grpc_java//netty",
    ],
    deps = [
        "//core/java/algebud",
        "//service/protos/algebud/service:algebud_service_java_grpc",
        "@io_grpc_grpc_java//api",
    ]
)
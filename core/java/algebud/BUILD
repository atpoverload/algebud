java_library(
    name = "algebud",
    visibility = ["//visibility:public"],
    srcs = glob(["*.java"]),
    deps = ["//core/java/algebud/token"]
)

java_binary(
    name = "cli",
    main_class = "algebud.AlgebudCLI",
    runtime_deps = [
        ":algebud",
        "//core/java/algebud/token",
    ]
)

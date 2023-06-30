java_library(
    name = "algebud",
    visibility = ["//visibility:public"],
    srcs = glob(["*.java"], exclude=["AlgebudCLI.java"]),
    deps = ["//core/java/algebud/token"]
)

java_binary(
    name = "cli",
    main_class = "algebud.AlgebudCLI",
    srcs = ["AlgebudCLI.java"],
    deps = [
        ":algebud",
        "//core/java/algebud/token",
    ]
)

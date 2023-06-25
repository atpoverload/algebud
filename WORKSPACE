# setting up rules to load deps
load("@bazel_tools//tools/build_defs/repo:git.bzl", "git_repository")
load("@bazel_tools//tools/build_defs/repo:http.bzl", "http_archive")

RULES_JVM_EXTERNAL_TAG = "4.5"
RULES_JVM_EXTERNAL_SHA ="b17d7388feb9bfa7f2fa09031b32707df529f26c91ab9e5d909eb1676badd9a6"

http_archive(
    name = "rules_jvm_external",
    sha256 = RULES_JVM_EXTERNAL_SHA,
    strip_prefix = "rules_jvm_external-%s" % RULES_JVM_EXTERNAL_TAG,
    url = "https://github.com/bazelbuild/rules_jvm_external/archive/%s.zip" % RULES_JVM_EXTERNAL_TAG,
)

load("@rules_jvm_external//:defs.bzl", "maven_install")

# TODO(timur): need to use this instead of manual parsing for the CLI
# maven_install(
#     name = "commons_cli_commons_cli",
#     artifacts = ["commons-cli:commons-cli:1.5.0"],
#     repositories = ["https://repo1.maven.org/maven2"],
# )

# java benchmarking (jmh)
http_archive(
    name = "bazel_skylib",
    sha256 = "66ffd9315665bfaafc96b52278f57c7e2dd09f5ede279ea6d39b2be471e7e3aa",
    urls = [
        "https://mirror.bazel.build/github.com/bazelbuild/bazel-skylib/releases/download/1.4.2/bazel-skylib-1.4.2.tar.gz",
        "https://github.com/bazelbuild/bazel-skylib/releases/download/1.4.2/bazel-skylib-1.4.2.tar.gz",
    ],
)

load("@bazel_skylib//:workspace.bzl", "bazel_skylib_workspace")

bazel_skylib_workspace()

maven_install(
    name = "org_openjdk_jmh_jmh_core",
    artifacts = [
      "org.openjdk.jmh:jmh-core:1.27",
    ],
    repositories = ["https://repo1.maven.org/maven2"],
)

maven_install(
    name = "org_openjdk_jmh_jmh_generator_annprocess",
    artifacts = [
      "org.openjdk.jmh:jmh-generator-annprocess:1.27"
    ],
    repositories = ["https://repo1.maven.org/maven2"],
)

java_plugin(
    name = "jmh_annotation_processor",
    deps = ["@org_openjdk_jmh_jmh_generator_annprocess"],
    processor_class = "org.openjdk.jmh.generators.BenchmarkProcessor",
    visibility = ["//visibility:private"],
)

java_binary(
  name = "evaluator_benchmark",
  main_class = "algebud.EvaluatorBenchmark",
  srcs = ["EvaluatorBenchmark.java"],
  deps = [
    "//core/java/algebud",
    "//core/java/algebud/token",
    "@org_openjdk_jmh_jmh_core",
  ],
  plugins = [":jmh_annotation_processor"]
)

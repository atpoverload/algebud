```sh
$ bazel run //core/jmh-benchmarks/algebud:evaluator_benchmark
```

```log
INFO: Analyzed target //core/jmh-benchmarks/algebud:evaluator_benchmark (0 packages loaded, 0 targets configured).
INFO: Found 1 target...
Target //core/jmh-benchmarks/algebud:evaluator_benchmark up-to-date:
  bazel-bin/core/jmh-benchmarks/algebud/evaluator_benchmark.jar
  bazel-bin/core/jmh-benchmarks/algebud/evaluator_benchmark
INFO: Elapsed time: 1.786s, Critical Path: 1.60s
INFO: 4 processes: 1 internal, 1 linux-sandbox, 2 worker.
INFO: Build completed successfully, 4 total actions
INFO: Running command line: bazel-bin/core/jmh-benchmarks/algebud/evaluator_benchmark
# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.euclideanDistance

# Run progress: 0.00% complete, ETA 01:06:40
# Fork: 1 of 5
# Warmup Iteration   1: 4935125.301 ops/s
# Warmup Iteration   2: 5086265.191 ops/s
# Warmup Iteration   3: 5064805.291 ops/s
# Warmup Iteration   4: 4909523.205 ops/s
# Warmup Iteration   5: 4945034.646 ops/s
Iteration   1: 5048382.908 ops/s
Iteration   2: 4952279.175 ops/s
Iteration   3: 5013191.990 ops/s
Iteration   4: 5013572.928 ops/s
Iteration   5: 5086437.463 ops/s

# Run progress: 2.50% complete, ETA 01:05:13
# Fork: 2 of 5
# Warmup Iteration   1: 5259378.821 ops/s
# Warmup Iteration   2: 5358368.972 ops/s
# Warmup Iteration   3: 5430872.215 ops/s
# Warmup Iteration   4: 5307198.640 ops/s
# Warmup Iteration   5: 5221541.209 ops/s
Iteration   1: 4973589.796 ops/s
Iteration   2: 5116233.081 ops/s
Iteration   3: 5256286.370 ops/s
Iteration   4: 5220500.298 ops/s
Iteration   5: 4965994.528 ops/s

# Run progress: 5.00% complete, ETA 01:03:31
# Fork: 3 of 5
# Warmup Iteration   1: 5325585.594 ops/s
# Warmup Iteration   2: 5291494.517 ops/s
# Warmup Iteration   3: 5300499.060 ops/s
# Warmup Iteration   4: 5599208.233 ops/s
# Warmup Iteration   5: 5639301.285 ops/s
Iteration   1: 5498068.267 ops/s
Iteration   2: 5695420.942 ops/s
Iteration   3: 5673701.864 ops/s
Iteration   4: 5677876.996 ops/s
Iteration   5: 5747911.261 ops/s

# Run progress: 7.50% complete, ETA 01:01:51
# Fork: 4 of 5
# Warmup Iteration   1: 4921777.917 ops/s
# Warmup Iteration   2: 5064023.187 ops/s
# Warmup Iteration   3: 5102141.487 ops/s
# Warmup Iteration   4: 5060512.349 ops/s
# Warmup Iteration   5: 5023077.571 ops/s
Iteration   1: 5087137.009 ops/s
Iteration   2: 5124050.785 ops/s
Iteration   3: ^[[17;5~5085199.016 ops/s
Iteration   4: 4967234.038 ops/s
Iteration   5: 5071167.871 ops/s

# Run progress: 10.00% complete, ETA 01:00:10
# Fork: 5 of 5
# Warmup Iteration   1: 5298772.485 ops/s
# Warmup Iterati5153360.280 ops/s
# Warmup Iteration   3: 5257266.302 ops/s
# Warmup Iteration   4: 5112436.334 ops/s
# Warmup Iteration   5: 5167354.180 ops/s
Iteration   1: 5358422.778 ops/s
Iteration   2: 5143228.024 ops/s
Iteration   3: 5211227.053 ops/s
Iteration   4: 5339329.884 ops/s
Iteration   5: 5245914.985 ops/s


Result "algebud.EvaluatorBenchmark.euclideanDistance":
  5222894.372 ±(99.9%) 187899.856 ops/s [Average]
  (min, avg, max) = (4952279.175, 5222894.372, 5747911.261), stdev = 250840.933
  CI (99.9%): [5034994.516, 5410794.229] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.euclideanDistanceNative

# Run progress: 12.50% complete, ETA 00:58:30
# Fork: 1 of 5
# Warmup Iteration   1: 162000900.137 ops/s
# Warmup Iteration   2: 162286863.701 ops/s
# Warmup Iteration   3: 216014363.541 ops/s
# Warmup Iteration   4: 216551325.782 ops/s
# Warmup Iteration   5: 215942287.800 ops/s
Iteration   1: 213954797.842 ops/s
Iteration   2: 217220748.762 ops/s
Iteration   3: 216761793.223 ops/s
Iteration   4: 213177474.510 ops/s
Iteration   5: 216815738.939 ops/s

# Run progress: 15.00% complete, ETA 00:56:50
# Fork: 2 of 5
# Warmup Iteration   1: 163091458.186 ops/s
# Warmup Iteration   2: 161175356.485 ops/s
# Warmup Iteration   3: 214204428.266 ops/s
# Warmup Iteration   4: 215154420.904 ops/s
# Warmup Iteration   5: 210018735.599 ops/s
Iteration   1: 216116372.076 ops/s
Iteration   2: 217101678.224 ops/s
Iteration   3: 215867549.093 ops/s
Iteration   4: 216629502.606 ops/s
Iteration   5: 217257574.846 ops/s

# Run progress: 17.50% complete, ETA 00:55:09
# Fork: 3 of 5
# Warmup Iteration   1: 159893277.636 ops/s
# Warmup Iteration   2: 163522609.462 ops/s
# Warmup Iteration   3: 216830857.754 ops/s
# Warmup Iteration   4: 216614757.189 ops/s
# Warmup Iteration   5: 216978835.676 ops/s
Iteration   1: 216511973.700 ops/s
Iteration   2: 216582269.177 ops/s
Iteration   3: 217405890.135 ops/s
Iteration   4: 216694422.117 ops/s
Iteration   5: 214268226.459 ops/s

# Run progress: 20.00% complete, ETA 00:53:29
# Fork: 4 of 5
# Warmup Iteration   1: 163148828.293 ops/s
# Warmup Iteration   2: 162517881.888 ops/s
# Warmup Iteration   3: 216783784.076 ops/s
# Warmup Iteration   4: 217646155.442 ops/s
# Warmup Iteration   5: 217580678.940 ops/s
Iteration   1: 216528140.384 ops/s
Iteration   2: 217104177.886 ops/s
Iteration   3: 216922584.761 ops/s
Iteration   4: 216637191.119 ops/s
Iteration   5: 216503025.509 ops/s

# Run progress: 22.50% complete, ETA 00:51:49
# Fork: 5 of 5
# Warmup Iteration   1: 162463697.249 ops/s
# Warmup Iteration   2: 162809679.556 ops/s
# Warmup Iteration   3: 215995213.632 ops/s
# Warmup Iteration   4: 217218656.859 ops/s
# Warmup Iteration   5: 215622415.946 ops/s
Iteration   1: 217064492.657 ops/s
Iteration   2: 217044924.893 ops/s
Iteration   3: 217517337.434 ops/s
Iteration   4: 217225447.202 ops/s
Iteration   5: 217608637.562 ops/s


Result "algebud.EvaluatorBenchmark.euclideanDistanceNative":
  216500878.845 ±(99.9%) 830771.037 ops/s [Average]
  (min, avg, max) = (213177474.510, 216500878.845, 217608637.562), stdev = 1109055.566
  CI (99.9%): [215670107.807, 217331649.882] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.euclideanDistanceNativeOptimized

# Run progress: 25.00% complete, ETA 00:50:08
# Fork: 1 of 5
# Warmup Iteration   1: 162381319.809 ops/s
# Warmup Iteration   2: 162859662.238 ops/s
# Warmup Iteration   3: 216576926.302 ops/s
# Warmup Iteration   4: 216350820.146 ops/s
# Warmup Iteration   5: 218393121.020 ops/s
Iteration   1: 217728579.968 ops/s
Iteration   2: 214166934.575 ops/s
Iteration   3: 217019050.432 ops/s
Iteration   4: 217824292.801 ops/s
Iteration   5: 215268104.354 ops/s

# Run progress: 27.50% complete, ETA 00:48:28
# Fork: 2 of 5
# Warmup Iteration   1: 162629743.157 ops/s
# Warmup Iteration   2: 163212550.489 ops/s
# Warmup Iteration   3: 217713387.492 ops/s
# Warmup Iteration   4: 217652228.423 ops/s
# Warmup Iteration   5: 212170113.802 ops/s
Iteration   1: 204793077.101 ops/s
Iteration   2: 214913409.246 ops/s
Iteration   3: 215596377.799 ops/s
Iteration   4: 213766138.887 ops/s
Iteration   5: 215853053.954 ops/s

# Run progress: 30.00% complete, ETA 00:46:48
# Fork: 3 of 5
# Warmup Iteration   1: 160894718.844 ops/s
# Warmup Iteration   2: 161091531.248 ops/s
# Warmup Iteration   3: 214804316.400 ops/s
# Warmup Iteration   4: 214153592.020 ops/s
# Warmup Iteration   5: 212271250.169 ops/s
Iteration   1: 215639277.865 ops/s
Iteration   2: 212605425.479 ops/s
Iteration   3: 212211767.788 ops/s
Iteration   4: 216066571.725 ops/s
Iteration   5: 215671025.301 ops/s

# Run progress: 32.50% complete, ETA 00:45:07
# Fork: 4 of 5
# Warmup Iteration   1: 160631094.857 ops/s
# Warmup Iteration   2: 160715400.644 ops/s
# Warmup Iteration   3: 213067437.216 ops/s
# Warmup Iteration   4: 214055292.778 ops/s
# Warmup Iteration   5: 213924682.173 ops/s
Iteration   1: 213427884.026 ops/s
Iteration   2: 210376473.050 ops/s
Iteration   3: 214775267.702 ops/s
Iteration   4: 212478667.218 ops/s
Iteration   5: 209526255.110 ops/s

# Run progress: 35.00% complete, ETA 00:43:27
# Fork: 5 of 5
# Warmup Iteration   1: 161956427.202 ops/s
# Warmup Iteration   2: 161686783.127 ops/s
# Warmup Iteration   3: 214676254.592 ops/s
# Warmup Iteration   4: 216032654.796 ops/s
# Warmup Iteration   5: 216124869.056 ops/s
Iteration   1: 210930469.548 ops/s
Iteration   2: 214871293.097 ops/s
Iteration   3: 215214878.685 ops/s
Iteration   4: 212320762.638 ops/s
Iteration   5: 212864594.471 ops/s


Result "algebud.EvaluatorBenchmark.euclideanDistanceNativeOptimized":
  213836385.313 ±(99.9%) 2147726.721 ops/s [Average]
  (min, avg, max) = (204793077.101, 213836385.313, 217824292.801), stdev = 2867153.725
  CI (99.9%): [211688658.592, 215984112.033] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.euclideanDistanceOptimized

# Run progress: 37.50% complete, ETA 00:41:47
# Fork: 1 of 5
# Warmup Iteration   1: 3031774.481 ops/s
# Warmup Iteration   2: 3215596.684 ops/s
# Warmup Iteration   3: 3121275.771 ops/s
# Warmup Iteration   4: 3185477.919 ops/s
# Warmup Iteration   5: 3152712.387 ops/s
Iteration   1: 3217677.649 ops/s
Iteration   2: 3205690.682 ops/s
Iteration   3: 3166044.330 ops/s
Iteration   4: 3209372.129 ops/s
Iteration   5: 3203551.157 ops/s

# Run progress: 40.00% complete, ETA 00:40:07
# Fork: 2 of 5
# Warmup Iteration   1: 3208728.518 ops/s
# Warmup Iteration   2: 3154557.351 ops/s
# Warmup Iteration   3: 3288806.163 ops/s
# Warmup Iteration   4: 3181546.928 ops/s
# Warmup Iteration   5: 3300166.625 ops/s
Iteration   1: 3292253.248 ops/s
Iteration   2: 3264305.743 ops/s
Iteration   3: 3271954.225 ops/s
Iteration   4: 3280583.363 ops/s
Iteration   5: 3287486.124 ops/s

# Run progress: 42.50% complete, ETA 00:38:26
# Fork: 3 of 5
# Warmup Iteration   1: 3133598.960 ops/s
# Warmup Iteration   2: 3173115.518 ops/s
# Warmup Iteration   3: 3227468.960 ops/s
# Warmup Iteration   4: 3177180.350 ops/s
# Warmup Iteration   5: 3138425.641 ops/s
Iteration   1: 3166083.532 ops/s
Iteration   2: 3208701.329 ops/s
Iteration   3: 3138444.842 ops/s
Iteration   4: 3052303.243 ops/s
Iteration   5: 2987168.137 ops/s

# Run progress: 45.00% complete, ETA 00:36:46
# Fork: 4 of 5
# Warmup Iteration   1: 3054994.885 ops/s
# Warmup Iteration   2: 2989567.255 ops/s
# Warmup Iteration   3: 2920393.506 ops/s
# Warmup Iteration   4: 2985635.128 ops/s
# Warmup Iteration   5: 3012317.806 ops/s
Iteration   1: 2971999.301 ops/s
Iteration   2: 2990522.675 ops/s
Iteration   3: 3021165.719 ops/s
Iteration   4: 3006731.981 ops/s
Iteration   5: 2992770.016 ops/s

# Run progress: 47.50% complete, ETA 00:35:06
# Fork: 5 of 5
# Warmup Iteration   1: 3143754.114 ops/s
# Warmup Iteration   2: 3205509.786 ops/s
# Warmup Iteration   3: 3251206.715 ops/s
# Warmup Iteration   4: 3232478.024 ops/s
# Warmup Iteration   5: 3275678.347 ops/s
Iteration   1: 3308723.023 ops/s
Iteration   2: 3270188.602 ops/s
Iteration   3: 3272634.010 ops/s
Iteration   4: 3286146.288 ops/s
Iteration   5: 3237713.258 ops/s


Result "algebud.EvaluatorBenchmark.euclideanDistanceOptimized":
  3172408.584 ±(99.9%) 87259.703 ops/s [Average]
  (min, avg, max) = (2971999.301, 3172408.584, 3308723.023), stdev = 116489.206
  CI (99.9%): [3085148.882, 3259668.287] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.quadraticFormula

# Run progress: 50.00% complete, ETA 00:33:25
# Fork: 1 of 5
# Warmup Iteration   1: 5361270.789 ops/s
# Warmup Iteration   2: 5175899.221 ops/s
# Warmup Iteration   3: 5394382.899 ops/s
# Warmup Iteration   4: 5400836.528 ops/s
# Warmup Iteration   5: 5437071.966 ops/s
Iteration   1: 5366297.264 ops/s
Iteration   2: 5412051.755 ops/s
Iteration   3: 5389484.387 ops/s
Iteration   4: 5386988.399 ops/s
Iteration   5: 5460551.645 ops/s

# Run progress: 52.50% complete, ETA 00:31:45
# Fork: 2 of 5
# Warmup Iteration   1: 5337575.629 ops/s
# Warmup Iteration   2: 5306397.545 ops/s
# Warmup Iteration   3: 5418691.226 ops/s
# Warmup Iteration   4: 5448664.756 ops/s
# Warmup Iteration   5: 5445272.921 ops/s
Iteration   1: 5414572.340 ops/s
Iteration   2: 5411100.375 ops/s
Iteration   3: 5318450.490 ops/s
Iteration   4: 5402323.176 ops/s
Iteration   5: 5262358.732 ops/s

# Run progress: 55.00% complete, ETA 00:30:05
# Fork: 3 of 5
# Warmup Iteration   1: 5047809.816 ops/s
# Warmup Iteration   2: 5722126.909 ops/s
# Warmup Iteration   3: 5509444.387 ops/s
# Warmup Iteration   4: 5473652.062 ops/s
# Warmup Iteration   5: 5458472.759 ops/s
Iteration   1: 5570438.963 ops/s
Iteration   2: 5469559.023 ops/s
Iteration   3: 5408497.200 ops/s
Iteration   4: 5440212.635 ops/s
Iteration   5: 5528757.351 ops/s

# Run progress: 57.50% complete, ETA 00:28:24
# Fork: 4 of 5
# Warmup Iteration   1: 4616461.979 ops/s
# Warmup Iteration   2: 4630817.775 ops/s
# Warmup Iteration   3: 4611449.242 ops/s
# Warmup Iteration   4: 4400064.340 ops/s
# Warmup Iteration   5: 4530015.505 ops/s
Iteration   1: 4663660.041 ops/s
Iteration   2: 4682681.611 ops/s
Iteration   3: 4719277.683 ops/s
Iteration   4: 4644254.864 ops/s
Iteration   5: 4637773.962 ops/s

# Run progress: 60.00% complete, ETA 00:26:44
# Fork: 5 of 5
# Warmup Iteration   1: 5083672.158 ops/s
# Warmup Iteration   2: 5174578.398 ops/s
# Warmup Iteration   3: 5157936.827 ops/s
# Warmup Iteration   4: 5161594.691 ops/s
# Warmup Iteration   5: 5102406.253 ops/s
Iteration   1: 5019351.240 ops/s
Iteration   2: 5239305.564 ops/s
Iteration   3: 5158961.808 ops/s
Iteration   4: 5050505.144 ops/s
Iteration   5: 5036188.245 ops/s


Result "algebud.EvaluatorBenchmark.quadraticFormula":
  5203744.156 ±(99.9%) 230679.066 ops/s [Average]
  (min, avg, max) = (4637773.962, 5203744.156, 5570438.963), stdev = 307949.954
  CI (99.9%): [4973065.090, 5434423.222] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.quadraticFormulaNative

# Run progress: 62.50% complete, ETA 00:25:04
# Fork: 1 of 5
# Warmup Iteration   1: 158614967.583 ops/s
# Warmup Iteration   2: 160597842.993 ops/s
# Warmup Iteration   3: 208460694.827 ops/s
# Warmup Iteration   4: 211971774.914 ops/s
# Warmup Iteration   5: 212169203.703 ops/s
Iteration   1: 211526257.447 ops/s
Iteration   2: 211847160.115 ops/s
Iteration   3: 211776622.626 ops/s
Iteration   4: 215045143.065 ops/s
Iteration   5: 213986727.269 ops/s

# Run progress: 65.00% complete, ETA 00:23:24
# Fork: 2 of 5
# Warmup Iteration   1: 159508004.534 ops/s
# Warmup Iteration   2: 160369271.634 ops/s
# Warmup Iteration   3: 212332201.209 ops/s
# Warmup Iteration   4: 212017602.307 ops/s
# Warmup Iteration   5: 215085758.641 ops/s
Iteration   1: 212791204.927 ops/s
Iteration   2: 215026444.134 ops/s
Iteration   3: 214631004.091 ops/s
Iteration   4: 211984797.799 ops/s
Iteration   5: 212711105.088 ops/s

# Run progress: 67.50% complete, ETA 00:21:43
# Fork: 3 of 5
# Warmup Iteration   1: 160627325.050 ops/s
# Warmup Iteration   2: 158615159.793 ops/s
# Warmup Iteration   3: 214124375.326 ops/s
# Warmup Iteration   4: 215682203.703 ops/s
# Warmup Iteration   5: 213567820.480 ops/s
Iteration   1: 212881196.157 ops/s
Iteration   2: 210050434.262 ops/s
Iteration   3: 213841654.100 ops/s
Iteration   4: 214167113.104 ops/s
Iteration   5: 213181199.558 ops/s

# Run progress: 70.00% complete, ETA 00:20:03
# Fork: 4 of 5
# Warmup Iteration   1: 162542377.568 ops/s
# Warmup Iteration   2: 161416576.826 ops/s
# Warmup Iteration   3: 212981462.719 ops/s
# Warmup Iteration   4: 215532790.996 ops/s
# Warmup Iteration   5: 216695248.626 ops/s
Iteration   1: 213901132.657 ops/s
Iteration   2: 215205673.238 ops/s
Iteration   3: 214670643.343 ops/s
Iteration   4: 215417663.547 ops/s
Iteration   5: 214948537.044 ops/s

# Run progress: 72.50% complete, ETA 00:18:23
# Fork: 5 of 5
# Warmup Iteration   1: 161842975.534 ops/s
# Warmup Iteration   2: 161440860.309 ops/s
# Warmup Iteration   3: 208571078.935 ops/s
# Warmup Iteration   4: 213799821.975 ops/s
# Warmup Iteration   5: 213598816.449 ops/s
Iteration   1: 215032317.209 ops/s
Iteration   2: 215112591.926 ops/s
Iteration   3: 215091066.538 ops/s
Iteration   4: 215623377.407 ops/s
Iteration   5: 215640976.116 ops/s


Result "algebud.EvaluatorBenchmark.quadraticFormulaNative":
  213843681.711 ±(99.9%) 1144579.348 ops/s [Average]
  (min, avg, max) = (210050434.262, 213843681.711, 215640976.116), stdev = 1527980.683
  CI (99.9%): [212699102.363, 214988261.059] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.quadraticFormulaNativeOptimized

# Run progress: 75.00% complete, ETA 00:16:42
# Fork: 1 of 5
# Warmup Iteration   1: 161048148.800 ops/s
# Warmup Iteration   2: 161653794.497 ops/s
# Warmup Iteration   3: 216335142.502 ops/s
# Warmup Iteration   4: 213645037.588 ops/s
# Warmup Iteration   5: 213641378.150 ops/s
Iteration   1: 216341727.699 ops/s
Iteration   2: 213239182.512 ops/s
Iteration   3: 215576504.315 ops/s
Iteration   4: 216219970.703 ops/s
Iteration   5: 214390221.453 ops/s

# Run progress: 77.50% complete, ETA 00:15:02
# Fork: 2 of 5
# Warmup Iteration   1: 161919427.685 ops/s
# Warmup Iteration   2: 162066082.410 ops/s
# Warmup Iteration   3: 213829093.589 ops/s
# Warmup Iteration   4: 216267804.457 ops/s
# Warmup Iteration   5: 216729706.452 ops/s
Iteration   1: 215395282.250 ops/s
Iteration   2: 216491517.349 ops/s
Iteration   3: 215556577.040 ops/s
Iteration   4: 214149506.491 ops/s
Iteration   5: 215949215.174 ops/s

# Run progress: 80.00% complete, ETA 00:13:22
# Fork: 3 of 5
# Warmup Iteration   1: 162002451.207 ops/s
# Warmup Iteration   2: 161973743.325 ops/s
# Warmup Iteration   3: 216170969.754 ops/s
# Warmup Iteration   4: 213930907.779 ops/s
# Warmup Iteration   5: 214224231.347 ops/s
Iteration   1: 215407094.180 ops/s
Iteration   2: 216135074.207 ops/s
Iteration   3: 216369292.723 ops/s
Iteration   4: 216480114.656 ops/s
Iteration   5: 215439166.181 ops/s

# Run progress: 82.50% complete, ETA 00:11:42
# Fork: 4 of 5
# Warmup Iteration   1: 161223897.701 ops/s
# Warmup Iteration   2: 161280205.277 ops/s
# Warmup Iteration   3: 215001283.910 ops/s
# Warmup Iteration   4: 213482393.528 ops/s
# Warmup Iteration   5: 214907565.447 ops/s
Iteration   1: 215206183.560 ops/s
Iteration   2: 213691098.375 ops/s
Iteration   3: 215463363.861 ops/s
Iteration   4: 215291139.281 ops/s
Iteration   5: 215155829.220 ops/s

# Run progress: 85.00% complete, ETA 00:10:01
# Fork: 5 of 5
# Warmup Iteration   1: 161876767.983 ops/s
# Warmup Iteration   2: 161915048.418 ops/s
# Warmup Iteration   3: 212753059.130 ops/s
# Warmup Iteration   4: 215399547.702 ops/s
# Warmup Iteration   5: 216027934.941 ops/s
Iteration   1: 214029344.766 ops/s
Iteration   2: 215660795.337 ops/s
Iteration   3: 216158536.990 ops/s
Iteration   4: 214333491.821 ops/s
Iteration   5: 216520679.645 ops/s


Result "algebud.EvaluatorBenchmark.quadraticFormulaNativeOptimized":
  215386036.392 ±(99.9%) 699907.468 ops/s [Average]
  (min, avg, max) = (213239182.512, 215386036.392, 216520679.645), stdev = 934356.445
  CI (99.9%): [214686128.924, 216085943.859] (assumes normal distribution)


# JMH version: 1.27
# VM version: JDK 17.0.4, OpenJDK 64-Bit Server VM, 17.0.4+8-Ubuntu-120.04
# VM invoker: /usr/lib/jvm/java-17-openjdk-amd64/bin/java
# VM options: <none>
# JMH blackhole mode: full blackhole + dont-inline hint; set -Djmh.blackhole.mode=COMPILER to get compiler-assisted ones
# Warmup: 5 iterations, 10 s each
# Measurement: 5 iterations, 10 s each
# Timeout: 10 min per iteration
# Threads: 1 thread, will synchronize iterations
# Benchmark mode: Throughput, ops/time
# Benchmark: algebud.EvaluatorBenchmark.quadraticFormulaOptimized

# Run progress: 87.50% complete, ETA 00:08:21
# Fork: 1 of 5
# Warmup Iteration   1: 4773445.978 ops/s
# Warmup Iteration   2: 5190244.339 ops/s
# Warmup Iteration   3: 5262401.947 ops/s
# Warmup Iteration   4: 5258524.098 ops/s
# Warmup Iteration   5: 5061505.289 ops/s
Iteration   1: 5207537.619 ops/s
Iteration   2: 5256135.639 ops/s
Iteration   3: 5113222.621 ops/s
Iteration   4: 5232375.741 ops/s
Iteration   5: 5232430.658 ops/s

# Run progress: 90.00% complete, ETA 00:06:41
# Fork: 2 of 5
# Warmup Iteration   1: 4694756.058 ops/s
# Warmup Iteration   2: 4836510.519 ops/s
# Warmup Iteration   3: 4801460.117 ops/s
# Warmup Iteration   4: 4755288.616 ops/s
# Warmup Iteration   5: 4825128.744 ops/s
Iteration   1: 4804528.190 ops/s
Iteration   2: 4709812.683 ops/s
Iteration   3: 4822420.067 ops/s
Iteration   4: 4875172.910 ops/s
Iteration   5: 4824580.005 ops/s

# Run progress: 92.50% complete, ETA 00:05:00
# Fork: 3 of 5
# Warmup Iteration   1: 5037112.588 ops/s
# Warmup Iteration   2: 5106481.947 ops/s
# Warmup Iteration   3: 5062785.625 ops/s
# Warmup Iteration   4: 5126163.658 ops/s
# Warmup Iteration   5: 5126495.405 ops/s
Iteration   1: 5108774.264 ops/s
Iteration   2: 5136775.339 ops/s
Iteration   3: 5088277.632 ops/s
Iteration   4: 5020880.019 ops/s
Iteration   5: 5103701.875 ops/s

# Run progress: 95.00% complete, ETA 00:03:20
# Fork: 4 of 5
# Warmup Iteration   1: 5022240.807 ops/s
# Warmup Iteration   2: 4972878.627 ops/s
# Warmup Iteration   3: 5067709.607 ops/s
# Warmup Iteration   4: 5097334.224 ops/s
# Warmup Iteration   5: 5035613.982 ops/s
Iteration   1: 5092116.355 ops/s
Iteration   2: 5100071.232 ops/s
Iteration   3: 4970062.056 ops/s
Iteration   4: 5098454.941 ops/s
Iteration   5: 5094133.517 ops/s

# Run progress: 97.50% complete, ETA 00:01:40
# Fork: 5 of 5
# Warmup Iteration   1: 5090994.174 ops/s
# Warmup Iteration   2: 4885925.638 ops/s
# Warmup Iteration   3: 5089008.118 ops/s
# Warmup Iteration   4: 5271438.192 ops/s
# Warmup Iteration   5: 5153201.926 ops/s
Iteration   1: 5257365.153 ops/s
Iteration   2: 5224461.235 ops/s
Iteration   3: 5226674.512 ops/s
Iteration   4: 5261165.255 ops/s
Iteration   5: 5235754.761 ops/s


Result "algebud.EvaluatorBenchmark.quadraticFormulaOptimized":
  5083875.371 ±(99.9%) 121673.598 ops/s [Average]
  (min, avg, max) = (4709812.683, 5083875.371, 5261165.255), stdev = 162430.772
  CI (99.9%): [4962201.774, 5205548.969] (assumes normal distribution)


# Run complete. Total time: 01:06:51

REMEMBER: The numbers below are just data. To gain reusable insights, you need to follow up on
why the numbers are the way they are. Use profilers (see -prof, -lprof), design factorial
experiments, perform baseline and negative tests that provide experimental control, make sure
the benchmarking environment is safe on JVM/OS/HW level, ask for reviews from the domain experts.
Do not assume the numbers tell you what you want them to tell.

Benchmark                                             Mode  Cnt          Score         Error  Units
EvaluatorBenchmark.euclideanDistance                 thrpt   25    5222894.372 ±  187899.856  ops/s
EvaluatorBenchmark.euclideanDistanceNative           thrpt   25  216500878.845 ±  830771.037  ops/s
EvaluatorBenchmark.euclideanDistanceNativeOptimized  thrpt   25  213836385.313 ± 2147726.721  ops/s
EvaluatorBenchmark.euclideanDistanceOptimized        thrpt   25    3172408.584 ±   87259.703  ops/s
EvaluatorBenchmark.quadraticFormula                  thrpt   25    5203744.156 ±  230679.066  ops/s
EvaluatorBenchmark.quadraticFormulaNative            thrpt   25  213843681.711 ± 1144579.348  ops/s
EvaluatorBenchmark.quadraticFormulaNativeOptimized   thrpt   25  215386036.392 ±  699907.468  ops/s
EvaluatorBenchmark.quadraticFormulaOptimized         thrpt   25    5083875.371 ±  121673.598  ops/s
```
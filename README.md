### Problem when using Retrofit 2.0.0-beta3 with logging-interceptor 3.0.1

This is as example of an issue I stumbled upon while trying to use [Retrofit](https://github.com/square/retrofit) version `com.squareup.retrofit2:retrofit:2.0.0-beta3` in with `com.squareup.okhttp3:logging-interceptor:3.0.1` (see the [build.gradle](https://github.com/tkirshboim/retrofit-and-interceptor-issue/blob/master/app/build.gradle#L22) file).

Using both these depencies together caused the following exception at Runtime which makes the library unuseable:

```
com.kirshboim.depepencyissue E/AndroidRuntime: FATAL EXCEPTION: OkHttp Dispatcher
  Process: com.kirshboim.depepencyissue, PID: 11732
  java.lang.AbstractMethodError: abstract method not implemented
    at retrofit2.OkHttpCall$1.onResponse(OkHttpCall.java)
    at okhttp3.RealCall$AsyncCall.execute(RealCall.java:133)
    at okhttp3.internal.NamedRunnable.run(NamedRunnable.java:33)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1112)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:587)
    at java.lang.Thread.run(Thread.java:841)
```
I'm guessing it's a (perhaps gradle related) dependency resolution issue because the workaround I used in the end was to use version `3.0.0-RC1` of the logging-interceptor library.

This is the dependecies gradle reports when it's broken:

```
./gradlew -q app:dependencies --configuration compile

------------------------------------------------------------
Project :app
------------------------------------------------------------

compile - Classpath for compiling the main sources.
+--- com.squareup.retrofit2:retrofit:2.0.0-beta3
|    \--- com.squareup.okhttp3:okhttp:3.0.0-RC1 -> 3.0.1
|         \--- com.squareup.okio:okio:1.6.0
\--- com.squareup.okhttp3:logging-interceptor:3.0.1
     \--- com.squareup.okhttp3:okhttp:3.0.1 (*)

(*) - dependencies omitted (listed previously)
```


And this is the dependecies gradle reports with the workaround:

```
./gradlew -q app:dependencies --configuration compile

------------------------------------------------------------
Project :app
------------------------------------------------------------

compile - Classpath for compiling the main sources.
+--- com.squareup.retrofit2:retrofit:2.0.0-beta3
|    \--- com.squareup.okhttp3:okhttp:3.0.0-RC1
|         \--- com.squareup.okio:okio:1.6.0
\--- com.squareup.okhttp3:logging-interceptor:3.0.0-RC1
     \--- com.squareup.okhttp3:okhttp:3.0.0-RC1 (*)

```

I thought I'd report this still so this doesn't happen in the future to others.

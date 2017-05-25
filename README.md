Logly
====
logly is a demo to show <br>
how to  check  application's performance by BugLog and record crash info when application run <br>
* * *
BugLog
====
# what is this for?<br>  
buglog is a log manager for application crash <br>  
when a crash happens in our application ,buglog will write those infos into a file<br>  
the crash include exception which uncaught and ANR question (html exception is considered )<br>   
the buglog file named "BugLog.txt"<br>  
and put in path "/storage/emulated/0/Android/data/package/files/apm_log/BugLog.txt"<br>  
* * *
# how to use it ?<br>  
it is very simple to use this<br>  

## start it in our application class <br>  
BugLog.getInstance().start(this);<br>  

## if you want to get the file <br>  
BugLog.getInstance().getBugLogFile();<br>  
* * *
# the BugLog.txt just like this<br>  
date：2017-05-25 14:29:52<br>  
----deviceInfo----<br>  
BOARD=MSM8916<br>  
versionCode=1<br>  
versionName=1.0<br>  

----netState----<br>  
networkState =networkWifi<br>  

----crashInfo----<br>  
java.lang.NullPointerException<br>  
	at com.rd.pflog.log.PFLogHandler.afterAddRecord(PFLogHandler.java:46) <br>  
	at com.rd.pmlog.PerformanceActivity.onClick(PerformanceActivity.java:45)<br>  
	at android.view.View.performClick(View.java:4496)<br>  
	at android.view.View$PerformClick.run(View.java:18603)<br>  
	at android.os.Handler.handleCallback(Handler.java:733)<br>  
	at android.os.Handler.dispatchMessage(Handler.java:95)<br>  
	at android.os.Looper.loop(Looper.java:136)<br>  
	at android.app.ActivityThread.main(ActivityThread.java:5426)<br>  
	at java.lang.reflect.Method.invokeNative(Native Method)<br>  
	at java.lang.reflect.Method.invoke(Method.java:515)<br>  
	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1268)<br>  
	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1084)<br>  
	at dalvik.system.NativeStart.main(Native Method)<br>  


package es.carlosdevops.repository.thread

import android.os.Handler
import android.os.Looper
/**
 * Created by carlosledesma on 31/1/18.
 */

fun MainThread(codeToRun: Runnable) {

    val uiHandler : Handler = Handler(Looper.getMainLooper())
    uiHandler.post(codeToRun)
}

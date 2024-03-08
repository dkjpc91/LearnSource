package com.mithilakshar.learnsource.repository

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import androidx.lifecycle.MutableLiveData
import com.mithilakshar.learnsource.utils.myResponses
import java.io.File

class repo(val context: Context) {

    private val downloadLd = MutableLiveData<myResponses<DownloadModel>>()
    val downloadLiveData get() = downloadLd
    val TAG = "Details_Activity"

    @SuppressLint("Range")
    suspend fun downloadpdf(url:String, fileName:String){
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS),fileName)
        if (file.exists()){
            val model = DownloadModel(
                progress = 100,
                isDownloaded =true ,
                downloadId = -1,
                filePath = file.toURI().toString()
            )
            downloadLiveData.postValue(myResponses.Success(model))
            return
        }

        downloadLiveData.postValue(myResponses.Loading())

        val downloadManager=context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        val downloadRequest= DownloadManager.Request(Uri.parse(url)).apply {
            setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
            setTitle(fileName)
            setDescription("Downloading Book")
            setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            setAllowedOverRoaming(true)
            setAllowedOverMetered(true)
            setDestinationInExternalFilesDir(context,Environment.DIRECTORY_DOWNLOADS,fileName)

        }
        val downloadId = downloadManager.enqueue(downloadRequest)

        var isDownloaded = false
        var progress = 0
        while (!isDownloaded) {
            val cursor = downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
            if (cursor.moveToNext()) {
                val status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                when(status){
                    DownloadManager.STATUS_RUNNING->{
                        val totalSize =
                            cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                        if (totalSize > 0) {
                            val downloadedBytesSize =
                                cursor.getLong(cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                            progress = ((downloadedBytesSize * 100L) / totalSize).toInt()
                            downloadLiveData.postValue(myResponses.Loading(progress))

                    }}
                    DownloadManager.STATUS_FAILED->{ val reason =
                        cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_REASON))
                        isDownloaded = true
                        downloadLiveData.postValue(myResponses.Error("Failed to Download $fileName.\nReason $reason"))
                        }
                    DownloadManager.STATUS_SUCCESSFUL->{ progress = 100
                        isDownloaded = true

                        val filePath =
                            cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI))
                        val model = DownloadModel(
                            progress = progress,
                            isDownloaded = isDownloaded,
                            downloadId = downloadId,
                            filePath = filePath
                        )

                        downloadLiveData.postValue(myResponses.Success(model))
                    }
                    DownloadManager.STATUS_PENDING->{downloadLiveData.postValue(myResponses.Loading(progress))}
                    DownloadManager.STATUS_PAUSED->{downloadLiveData.postValue(myResponses.Loading(progress))}


                }

            }

        }





    }

    data class DownloadModel(
        var progress: Int = 0,
        var isDownloaded: Boolean,
        var downloadId: Long,
        var filePath: String
    )
}
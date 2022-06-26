package com.dark.captions.Adapter

import android.app.PendingIntent.getActivity
import android.content.*
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dark.captions.AllCaption
import com.dark.captions.BuildConfig
import com.dark.captions.Model.CaptionModel
import com.dark.captions.databinding.ItemCaptionBinding
import java.util.*


class AllCaptionssAdapter(val allCaption: AllCaption,val captionList: ArrayList<CaptionModel>) : RecyclerView.Adapter<AllCaptionssAdapter.CaptionViewHolder>() {
    class CaptionViewHolder(val binding: ItemCaptionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CaptionViewHolder {
       return CaptionViewHolder(ItemCaptionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CaptionViewHolder, position: Int) {
        holder.binding.quotesTEXT.text= captionList[position].data.toString()


        holder.binding.copyBtn.setOnClickListener {

            var clipboard = allCaption.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var clip = ClipData.newPlainText("label",captionList[position].data.toString() )
            clipboard?.setPrimaryClip (clip)

            Toast.makeText(allCaption, "Copy Done!", Toast.LENGTH_SHORT).show()
        }

        holder.binding.shareBtn.setOnClickListener {

            try {
                val shareIntent = Intent(Intent.ACTION_SEND)
                shareIntent.type = "text/plain"
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name")
                var shareMessage = "\n${captionList[position].data}\n\n"
                shareMessage =
                    """
                   ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
                   
                   
                   """.trimIndent()
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
                allCaption.startActivity(Intent.createChooser(shareIntent, "choose one"))
            } catch (e: Exception) {
                //e.toString();
            }
        }

        holder.binding.send.setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.setPackage("com.whatsapp")
            whatsappIntent.putExtra(
                Intent.EXTRA_TEXT,
                captionList[position].data.toString()
            )
            try {
                allCaption.startActivity(whatsappIntent)
            } catch (ex: ActivityNotFoundException) {

            }
        }

    }

    override fun getItemCount()= captionList.size
}
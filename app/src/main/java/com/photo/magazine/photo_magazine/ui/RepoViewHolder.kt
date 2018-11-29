package com.photo.magazine.photo_magazine.ui

import android.content.Intent
import android.net.Uri
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.photo.magazine.photo_magazine.R
import com.photo.magazine.photo_magazine.model.Repo

class RepoViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val title: TextView = view.findViewById(R.id.title)
    private val content: TextView = view.findViewById(R.id.content)

    private var repo: Repo? = null

    init {
        view.setOnClickListener{
            Toast.makeText(view.context, "show click", Toast.LENGTH_LONG).show()
            repo?.url?.let{url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.zhihu.com/question/22107626"))
                view.context.startActivity(intent)

            }
        }
    }

    fun bind(repo:Repo?) {
        if (repo ==null) {
            title.text = itemView.resources.getString(R.string.app_name)
            content.text = itemView.resources.getString(R.string.app_name)
        } else {
            title.text = repo.title
            content.text = repo.excerpt.toString()
        }
    }

    companion object {
        fun create(parent: ViewGroup): RepoViewHolder{
            val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_view_item, parent, false)
            return RepoViewHolder(view)
        }
    }
}
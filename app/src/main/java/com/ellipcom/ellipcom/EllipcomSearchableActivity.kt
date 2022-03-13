package com.ellipcom.ellipcom

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EllipcomSearchableActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ellipcom_searchable)
        setDefaultKeyMode(DEFAULT_KEYS_SEARCH_LOCAL)

        //verifying the action and query
        if (Intent.ACTION_SEARCH == intent.action) {

            intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }
        }
    }

    private fun doMySearch(query: String) {
        //actual search operation takes place here
        Toast.makeText(this, "query", Toast.LENGTH_SHORT).show()
    }
}
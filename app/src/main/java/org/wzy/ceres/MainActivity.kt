package org.wzy.ceres

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var budget: TextView
    private lateinit var adapter: MainActivity.Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        budget = findViewById(R.id.budget)

        recyclerView.layoutManager = LinearLayoutManager(MainActivity@this)
        adapter = Adapter(MainActivity@this)
        recyclerView.adapter = adapter
    }

    class Adapter(private val ctx: Context): RecyclerView.Adapter<ItemViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(ctx).inflate(R.layout.item_consume, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(position.toString())
        }
    }

    class ItemViewHolder(item: View): RecyclerView.ViewHolder(item) {

        private val title: TextView

        init {
            title = itemView.findViewById(R.id.title)
        }

        fun bind(titleText: String) {
            title.text = titleText
        }
    }
}

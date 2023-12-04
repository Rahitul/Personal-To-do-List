package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap.Title
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
    private val todos: MutableList<Todo>
): RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.itemtodo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteDoneTodos(){
        todos.removeAll{todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvToolTitle: TextView,isChecked: Boolean){
        if(isChecked){
            tvToolTitle.paintFlags = tvToolTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvToolTitle.paintFlags=tvToolTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            val tvTodoTile:TextView=
                findViewById(R.id.tvToolTitle)
            val cbDone:CheckBox=
            findViewById(R.id.cbDone)
            tvTodoTile.text=curTodo.title
            cbDone.isChecked=curTodo.isChecked

            toggleStrikeThrough(tvTodoTile,curTodo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvTodoTile,isChecked)
                curTodo.isChecked=curTodo.isChecked

            }


        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
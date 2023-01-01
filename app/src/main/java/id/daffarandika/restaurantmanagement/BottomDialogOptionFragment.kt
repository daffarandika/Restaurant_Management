package id.daffarandika.restaurantmanagement

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.adapter.EmployeeAdapter
import id.daffarandika.restaurantmanagement.adapter.MemberAdapter
import id.daffarandika.restaurantmanagement.model.Employee
import id.daffarandika.restaurantmanagement.model.Member
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class BottomDialogOptionFragment: Fragment(R.layout.fragment_bottom_dialog_option), RecyclerViewEvent {

    var employees: MutableList<Employee> = mutableListOf()
    var members: MutableList<Member> = mutableListOf()
    private val TAG = "BottomDialogOptionFragm"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rvBottomDialogOption)
        val args = arguments?.getString("title")

        if (args == "Member") {
            getMembers()
            val adapter = MemberAdapter(requireContext(), members, this)
            rv.adapter = adapter
            rv.layoutManager = LinearLayoutManager(context)
        } else {
            getEmployee()
            val adapter = EmployeeAdapter(requireContext(), employees, this)
            rv.adapter =  adapter
            rv.layoutManager = LinearLayoutManager(context)
        }
    }

    private fun getMembers() = runBlocking {
        launch(Dispatchers.IO) {
            val conn = URL(CONSTS.url + "/member").openConnection() as HttpURLConnection
            val inputStream = conn.inputStream.bufferedReader().readText()
            val jsonArray = JSONArray(inputStream)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                members.add(Member(
                    jsonObject.getString("memberid"),
                    jsonObject.getString("name"),
                    jsonObject.getString("email"),
                    jsonObject.getString("handphone"),
                    jsonObject.getString("joindate")
                ))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onItemClick(position: Int) {
        val employee = employees[position]
        Toast.makeText(context, employee.name, Toast.LENGTH_SHORT).show()
    }
    fun getEmployee () = runBlocking{
        launch(Dispatchers.IO) {
            val conn = URL(CONSTS.url+"/employee").openConnection() as HttpURLConnection
            val inputString = conn.inputStream.bufferedReader().readText()
            val jsonArray = JSONArray(inputString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                employees.add(Employee(
                    jsonObject.getString("employeeid"),
                    jsonObject.getString("name"),
                    jsonObject.getString("email"),
                    jsonObject.getString("handphone")
                ))
            }
            Log.d(TAG, "onCreate: $employees")

        }
    }
}
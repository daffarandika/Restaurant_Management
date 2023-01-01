package id.daffarandika.restaurantmanagement

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.daffarandika.restaurantmanagement.adapter.BottomDialogAdapter
import id.daffarandika.restaurantmanagement.model.BottomSheetItem

class BottomDialogHomeFragment : Fragment(), RecyclerViewEvent {

    val bottons_title: MutableList<BottomSheetItem> = mutableListOf(
        BottomSheetItem(R.drawable.employee, "Employee"),
        BottomSheetItem(R.drawable.member, "Member"),
        BottomSheetItem(R.drawable.detailorder, "Detail Order"),
        BottomSheetItem(R.drawable.headorder, "Head Order"),
        BottomSheetItem(R.drawable.menu_restaurant, "Menu"),
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_dialog_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BottomDialogAdapter(requireContext() , bottons_title, this)
        val rv = view.findViewById<RecyclerView>(R.id.rvBottomDialog)
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(context, 2)
    }

    override fun onItemClick(position: Int) {
        val title = bottons_title[position].title
        Toast.makeText(requireContext(), title, Toast.LENGTH_SHORT).show()
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            val args: Bundle = Bundle()
            val testFragment = TestFragment()
            args.putString("title", bottons_title[position].title)
            testFragment.arguments = args
            replace(R.id.fragment, testFragment)
            commit()
        }
    }

}
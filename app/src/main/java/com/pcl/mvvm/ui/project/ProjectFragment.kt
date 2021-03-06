package com.pcl.mvvm.ui.project

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.aleyn.mvvm.base.BaseFragment
import com.aleyn.mvvm.event.Message
import com.pcl.mvvm.R
import com.pcl.mvvm.databinding.ProjectFragmentBinding
import com.pcl.mvvm.network.entity.ArticlesBean
import com.pcl.mvvm.ui.detail.DetailActivity

class ProjectFragment : BaseFragment<ProjectViewModel, ProjectFragmentBinding>() {


    companion object {
        fun newInstance() = ProjectFragment()
    }

    override fun layoutId() = R.layout.project_fragment

    override fun initView(savedInstanceState: Bundle?) {
        mBinding?.viewModel = viewModel
    }

    override fun lazyLoadData() {
        viewModel.getProjectType()
        viewModel.getProjectList(294)
        viewModel.defUI.msgEvent.observe(viewLifecycleOwner, Observer {
            val msg = it
            when (msg.code) {
                0 -> {
                }
            }
        })
    }

    override fun handleEvent(msg: Message) {
        when (msg.code) {
            0 -> {
                val bean = msg.obj as ArticlesBean
                val intent = Intent().apply {
                    setClass(activity!!, DetailActivity::class.java)
                    putExtra("url", bean.link)
                }
                startActivity(intent)
            }
        }
    }
}

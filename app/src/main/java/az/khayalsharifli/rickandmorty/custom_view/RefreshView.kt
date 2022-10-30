package az.khayalsharifli.rickandmorty.custom_view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import az.khayalsharifli.rickandmorty.R
import me.dkzwm.widget.srl.SmoothRefreshLayout
import me.dkzwm.widget.srl.extra.IRefreshView
import me.dkzwm.widget.srl.indicator.IIndicator

class RefreshView(context: Context) : FrameLayout(context), IRefreshView<IIndicator> {

    init {
        val view: View = LayoutInflater.from(context).inflate(R.layout.pull_refresh_layout, this)
    }


    override fun getType(): Int {
        return IRefreshView.TYPE_HEADER.toInt()
    }

    override fun getStyle(): Int {
        return IRefreshView.STYLE_PIN.toInt()
    }

    override fun getCustomHeight(): Int {
        return 0
    }

    override fun getView(): View {
        return this
    }

    override fun onFingerUp(layout: SmoothRefreshLayout?, indicator: IIndicator?) {
    }

    override fun onReset(layout: SmoothRefreshLayout?) {
    }

    override fun onRefreshPrepare(layout: SmoothRefreshLayout?) {
    }

    override fun onRefreshBegin(layout: SmoothRefreshLayout?, indicator: IIndicator?) {
    }

    override fun onRefreshComplete(layout: SmoothRefreshLayout?, isSuccessful: Boolean) {
    }

    override fun onRefreshPositionChanged(
        layout: SmoothRefreshLayout?,
        status: Byte,
        indicator: IIndicator?
    ) {
    }

    override fun onPureScrollPositionChanged(
        layout: SmoothRefreshLayout?,
        status: Byte,
        indicator: IIndicator?
    ) {
    }

}
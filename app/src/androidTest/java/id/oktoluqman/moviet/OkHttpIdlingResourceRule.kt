package id.oktoluqman.moviet

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import com.jakewharton.espresso.OkHttp3IdlingResource
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

// modified from: https://medium.com/insiden26/okhttp-idling-resource-for-espresso-462ef2417049
class OkHttpIdlingResourceRule : TestRule {
    private val resource: IdlingResource = OkHttp3IdlingResource.create(
        "okhttp",
        OkHttpClientProvider.instance
    )

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                IdlingRegistry.getInstance().register(resource)
                base.evaluate()
                IdlingRegistry.getInstance().unregister(resource)
            }
        }
    }
}

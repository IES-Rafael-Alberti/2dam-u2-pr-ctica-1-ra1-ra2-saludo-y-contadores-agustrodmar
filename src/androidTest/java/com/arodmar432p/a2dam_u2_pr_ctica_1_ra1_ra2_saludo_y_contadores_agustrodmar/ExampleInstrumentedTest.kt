package com.arodmar432p.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_agustrodmar

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(
            "com.arodmar432p.a2dam_u2_pr_ctica_1_ra1_ra2_saludo_y_contadores_agustrodmar",
            appContext.packageName
        )
    }
}
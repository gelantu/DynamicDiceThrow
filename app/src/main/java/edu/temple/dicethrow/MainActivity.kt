package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {
    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        if (savedInstanceState == null) {
            loadInitialFragments()
        }
    }
    /* TODO 1: Load fragment(s)
       - Show only Button Fragment if portrait
       - show both fragments if Landscape
     */

    private fun loadInitialFragments() {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container1, ButtonFragment())
        if (isLandscape) {
            transaction.replace(R.id.container2, DieFragment())
        }
        transaction.commit()
    }

    /* TODO 2: switch fragments if portrait (no need to switch fragments if Landscape)
        */
    // Remember to place Fragment transactions on BackStack so then can be reversed

    override fun buttonClicked() {
        if (!isLandscape) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container1, DieFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
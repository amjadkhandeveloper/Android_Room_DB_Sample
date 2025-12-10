package demo.amjadkhan.roomdatabasesample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import demo.amjadkhan.roomdatabasesample.db.AppDatabase
import demo.amjadkhan.roomdatabasesample.entities.Contact
import demo.amjadkhan.roomdatabasesample.ui.theme.RoomDatabaseSampleTheme
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : ComponentActivity() {
//    private lateinit var database: AppDatabase
    private val database by lazy {
        AppDatabase.getDatabase(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        database= AppDatabase.getDatabase(applicationContext)

        enableEdgeToEdge()

        lifecycleScope.launch {
            database.ContactDao().insertContact(
                Contact(1, "PersonOne", Date())
            )
        }

        setContent {
            RoomDatabaseSampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Load Contact",
                        modifier =
                            Modifier
                                .padding(innerPadding)
                                .clickable(
                                    onClick = {
                                      getContactList()
                                    }
                                )
                    )
                }
            }
        }




        lifecycleScope.launch {
            database.ContactDao().insertContact(Contact(2, "PersonTwo", Date()))
            database.ContactDao().insertContact(Contact(3, "PersonThree", Date()))
            database.ContactDao().insertContact(Contact(4, "PersonFour", Date()))
        }

    }

    fun getContactList(){
        database.ContactDao().getContact().observe(this) { contactList ->
            print("List of contact : $contactList")
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RoomDatabaseSampleTheme {
        Greeting("Android")
    }
}
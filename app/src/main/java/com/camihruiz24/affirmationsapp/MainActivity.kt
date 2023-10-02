package com.camihruiz24.affirmationsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.camihruiz24.affirmationsapp.data.Datasource
import com.camihruiz24.affirmationsapp.data.model.Affirmation
import com.camihruiz24.affirmationsapp.ui.theme.AffirmationsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Affirmations()
                }
            }
        }
    }
}

@Composable
fun Affirmations() {

    LazyColumn(
        contentPadding = PaddingValues(16.dp)
    ) {
        items(Datasource().loadAffirmations()) { affirmation ->
            BeautifulCard(affirmation)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun BeautifulCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background),
        elevation = CardDefaults.cardElevation()
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(id = R.string.inspirational_image),
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = stringResource(affirmation.stringResourceId),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )

            Spacer(modifier = modifier.height(4.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AffirmationsPreview() {
    AffirmationsAppTheme {
        Affirmations()
    }
}

@Preview
@Composable
fun BeautifulCardPreview() {
    BeautifulCard(affirmation = Affirmation(R.string.affirmation1, R.drawable.image1))
}
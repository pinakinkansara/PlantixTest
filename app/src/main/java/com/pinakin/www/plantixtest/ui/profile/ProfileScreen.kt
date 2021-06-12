package com.pinakin.www.plantixtest.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.coil.rememberCoilPainter
import com.google.accompanist.imageloading.ImageLoadState
import com.pinakin.www.plantixtest.model.Profile

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileListViewModel) {
    val profiles = viewModel.profiles.observeAsState(initial = emptyList())

    ProfileList(profiles = profiles.value) {

    }
}

@Composable
fun ProfileList(profiles: List<Profile>, onProfileSelected: (Profile) -> Unit) {
    Surface(color = MaterialTheme.colors.surface) {
        LazyColumn() {
            items(profiles) { profile ->
                ProfileListItem(profile = profile) { profile ->
                    onProfileSelected(profile)
                }
            }
        }
    }

}

@Composable
fun ProfileListItem(profile: Profile, onProfileClick: (Profile) -> Unit) {

    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable {
                onProfileClick(profile)
            }
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)) {
            val painter = rememberCoilPainter(profile.profilePic)
            Box {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(64.dp)
                        .clip(CircleShape)
                )

                when (painter.loadState) {
                    is ImageLoadState.Loading -> {
                        // Display a circular progress indicator whilst loading
                        CircularProgressIndicator(Modifier.align(Alignment.Center))
                    }
                    is ImageLoadState.Error -> {
                        // If you wish to display some content if the request fails
                    }
                }
            }
            val name = "${profile.firstName.capitalize(Locale.current)} ${
                profile.lastName.capitalize(Locale.current)
            }"
            Text(
                name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                fontWeight = FontWeight.SemiBold
            )
        }

    }
}
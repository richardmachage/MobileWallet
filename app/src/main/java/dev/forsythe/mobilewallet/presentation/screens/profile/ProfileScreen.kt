package dev.forsythe.mobilewallet.presentation.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import dev.forsythe.mobilewallet.R
import dev.forsythe.mobilewallet.presentation.components.CircularImage
import dev.forsythe.mobilewallet.presentation.components.buttons.BackButton
import dev.forsythe.mobilewallet.presentation.components.spacers.VerticalSpacer
import dev.forsythe.mobilewallet.presentation.components.texts.BoldText
import dev.forsythe.mobilewallet.presentation.components.xOffset
import dev.forsythe.mobilewallet.presentation.components.yOffset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController
){
    val profileViewModel = hiltViewModel<ProfileViewModel>()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    val customer by remember { profileViewModel.getCustomerDetails() }.collectAsState(null)

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LargeTopAppBar(
                        title = { BoldText(text = "My Profile") },
                        navigationIcon = {
                            BackButton(onClick = {navController.navigateUp()})
                        },
                        scrollBehavior = scrollBehavior
                    )

                    AnimatedVisibility(
                        scrollBehavior.state.collapsedFraction < 0.5
                    ) {

                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Box {
                                CircularImage(
                                    image = painterResource(R.mipmap.blank_profile),
                                    height = 200,
                                    width = 200,
                                    contentScale = ContentScale.Crop
                                )

                                IconButton(
                                    modifier = Modifier
                                        .yOffset(5)
                                        .xOffset(10)
                                        .align(
                                            Alignment.BottomEnd
                                        ),
                                    onClick = {
                                        //TODO
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "edit",
                                    )
                                }
                            }
                        }
                    }
                }
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            customer?.let {customerModel->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                ) {
                    VerticalSpacer(20)
                    HorizontalDivider()
                    VerticalSpacer(10)
                    // VerticalSpacer(10)
                    //name
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Name",
                            fontWeight = FontWeight.Light,
                        )
                        VerticalSpacer(5)
                        BoldText(
                            text = customerModel.firstName + " " + customerModel.lastName
                        )
                    }
                    VerticalSpacer(10)
                    HorizontalDivider()
                    VerticalSpacer(10)

                    //Email
                    Column(

                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Email",//stringResource(R.string.feature_groups_group_name_label)
                            fontWeight = FontWeight.Light,
                        )
                        VerticalSpacer(5)
                        BoldText(
                            text = customerModel.email
                        )
                    }

                    VerticalSpacer(10)
                    HorizontalDivider()
                    VerticalSpacer(10)

                    //Id
                    Column(

                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Customer Id",//stringResource(R.string.feature_groups_group_name_label)
                            fontWeight = FontWeight.Light,
                        )
                        VerticalSpacer(5)
                        BoldText(
                            text = customerModel.id
                        )
                    }
                    VerticalSpacer(10)
                    HorizontalDivider()
                    VerticalSpacer(10)
                    //Account
                    Column(

                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Account No",
                            fontWeight = FontWeight.Light,
                        )
                        VerticalSpacer(5)
                        BoldText(
                            text = customerModel.account
                        )

                    }
                    VerticalSpacer(10)
                    HorizontalDivider()
                }
            }
        }
    }
}
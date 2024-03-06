//package com.licoding.oceanpulse.presentation.Main.search.components
//
//LazyColumn(
//modifier = Modifier
//.padding(horizontal = 16.dp, vertical = 75.dp)
//) {
//    item {
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(10.dp))
//                .padding(10.dp)
//        ) {
//            Column(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Did you know:",
//                    fontSize = 30.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Over 50% of oxygen on the planet comes from oceans."
//                )
//                Spacer(modifier = Modifier.height(16.dp))
//                Text(
//                    text = "Read more",
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable(
//                            onClick = {
//                                openAlertDialog = true
//                            },
//                            interactionSource = remember { MutableInteractionSource() },
//                            indication = null
//                        ),
//                    textAlign = TextAlign.End
//                )
//            }
//
//            if (openAlertDialog) {
//                MinimalDialog(onDismissRequest)
//            }
//        }
//    }
//    item {
//        Spacer(modifier = Modifier.height(16.dp))
//        TabNavigation(context)
//    }
//}
//}
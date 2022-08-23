package com.example.linkaja.ui.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PermContactCalendar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.linkaja.R
import com.example.linkaja.ui.theme.mulishFamily

@Composable
fun HomeScreen() {
    val defaultOffsetTransfer = 60f
    val defaultOffsetAddPayment = 210f
    val defaultOffsetMenu = 266f

    var offsetTranfer by remember { mutableStateOf(defaultOffsetTransfer) }
    var offsetAddPayment by remember { mutableStateOf(defaultOffsetAddPayment) }
    var offsetMenu by remember { mutableStateOf(defaultOffsetMenu) }

    Box(modifier = Modifier.offset(y = 0.dp)) {
        HomeTopBar()
    }
    Box(
        Modifier
            .fillMaxSize()
            .scrollable(
                state = rememberScrollableState { delta ->
                    offsetTranfer += delta
                    offsetAddPayment += delta
                    offsetMenu += delta
                    delta
                },
                orientation = Orientation.Vertical
            )
    ) {
        if (offsetTranfer <= 0f) {
            offsetTranfer = 0f
        } else if (offsetTranfer > defaultOffsetTransfer) {
            offsetTranfer = defaultOffsetTransfer
        }

        if (offsetAddPayment <= 0f) {
            offsetAddPayment = 0f
        } else if (offsetAddPayment > defaultOffsetAddPayment) {
            offsetAddPayment = defaultOffsetAddPayment
        }

        if (offsetMenu <= 0f) {
            offsetMenu = 0f
        } else if (offsetMenu > defaultOffsetMenu) {
            offsetMenu = defaultOffsetMenu
        }
        Box(
            Modifier
                .offset(x = 0.dp, y = offsetTranfer.dp)
                .fillMaxWidth()
                .height(200.dp)
                .background(
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color = Color.Red
                )
        ) {
            BalanceCard()
        }
        Box(
            Modifier
                .offset(x = 0.dp, y = offsetAddPayment.dp)
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
                    color = Color(0xFFE3E9ED)
                )
        ) {
            AddMethod()
        }
        Card(
            Modifier
                .offset(x = 0.dp, y = offsetMenu.dp)
                .fillMaxWidth()
                .height(700.dp)
                .background(color = Color.White),
            shape = RectangleShape,
            elevation = 10.dp
        ) {
            HomeBody()
        }

    }
}

@Composable
fun HomeTopBar() {
    Row(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = "https://i.ibb.co/3N3w20Q/Group-1.png",
            contentDescription = null,
            modifier = Modifier
                .width(32.dp)
                .height(32.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text("MUHAMMAD SOFYAN", fontWeight = FontWeight.SemiBold, fontSize = 15.sp, modifier = Modifier.padding(top = 2.dp))
        Spacer(modifier = Modifier.width(100.dp))
        Icon(imageVector = Icons.Filled.PermContactCalendar, contentDescription = null, tint = Color.Gray)
        Spacer(modifier = Modifier.width(14.dp))
        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null, tint = Color.Gray)
    }
}


@Composable
fun BalanceCard() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(text = "Rp. 8.123.456", color = Color.White, fontWeight = FontWeight.Medium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Saldo Bonus 10.000  ", color = Color.White, fontWeight = FontWeight.Light, fontSize = 12.sp, fontFamily = mulishFamily)
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_circle_right_white), contentDescription = null, modifier = Modifier
                        .width(16.dp)
                        .height(18.dp), tint = Color.White)
                }
            }
            AsyncImage(
                model = "https://upload.wikimedia.org/wikipedia/commons/thumb/8/85/LinkAja.svg/2048px-LinkAja.svg.png",
                contentDescription = null,
                modifier = Modifier
                    .width(32.dp)
                    .height(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth()) {
            BalanceItem(id = R.drawable.ic_send_money_white, title = "Kirim Uang")
            BalanceItem(id = R.drawable.ic_fill_balance_white, title = "Isi Saldo")
            BalanceItem(id = R.drawable.ic_airplane_ticket_white, title = "Beli Tiket")
            BalanceItem(id = R.drawable.ic_all_white, title = "Semua")
        }
    }
}

@Composable
fun BalanceItem(@DrawableRes id: Int, title: String ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = id), contentDescription = null, modifier = Modifier.size(48.dp))
        Text(text = title, color = Color.White, fontWeight = FontWeight.Light, fontSize = 11.sp, fontFamily = mulishFamily)
    }
}

@Composable
fun AddMethod() {
    Row(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = R.drawable.ic_debit_card),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(6.dp))
        Column() {
            Text(text = "Tambah Metode Pembayaran", fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
            Text(text = "Hubungkan kartu debit dan sumber dana lain", fontWeight = FontWeight.Light, fontSize = 11.sp)
        }
        Spacer(modifier = Modifier.width(56.dp))
        Icon(
            Icons.Default.Add,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier
                .background(color = Color.White, shape = CircleShape)
                .padding(2.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeBody() {
    val list = listOf<ItemMenu>(
        ItemMenu(id = R.drawable.pulsadata, title = "Pulsa/Data"),
        ItemMenu(id = R.drawable.electricity, title = "Electricity"),
        ItemMenu(id = R.drawable.emoney, title = "E-Money"),
        ItemMenu(id = R.drawable.games, title = "Games"),
        ItemMenu(id = R.drawable.transport, title = "Transportasi"),
        ItemMenu(id = R.drawable.berbagi, title = "Berbagi"),
        ItemMenu(id = R.drawable.pdam, title = "Air"),
        ItemMenu(id = R.drawable.more, title = "Semua")
    )
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {

        LazyVerticalGrid(
            cells = GridCells.Fixed(4)
        ){
            items(list.size) { index ->
                MenuItem(id = list[index].id, list[index].title)
            }
        }
    }
}

@Composable
fun MenuItem(@DrawableRes id: Int, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, ) {
        Image(painter = painterResource(id = id), contentDescription = null, modifier = Modifier.padding(top = 16.dp).size(48.dp))
        Text(title, fontWeight = FontWeight.Normal, fontSize = 12.sp, modifier = Modifier.padding(top = 12.dp))
    }
}

data class ItemMenu(
    @DrawableRes
    val id: Int,
    val title: String
)

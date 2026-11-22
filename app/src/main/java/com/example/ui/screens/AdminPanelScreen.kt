package com.example.ui.screens

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.data.UiStrings
import com.example.model.AdmissionStream
import com.example.model.AppLanguage
import com.example.model.LocalizedText
import com.example.model.PortalCategory
import com.example.model.PortalUpdateItem
import com.example.model.ScholarshipCategory
import com.example.model.SchemeScope
import com.example.ui.components.DemoBadge
import com.example.ui.components.LanguageTogglePill
import com.example.ui.components.TricolorAccentBar
import com.example.viewmodel.SahayakUiState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminPanelScreen(
    uiState: SahayakUiState,
    onBack: () -> Unit,
    onToggleLanguage: () -> Unit,
    onSaveItem: (PortalUpdateItem) -> Unit,
    onDeleteItem: (String) -> Unit,
    onResetDemoCatalog: () -> Unit,
    onUpdatePin: (String) -> Unit,
    onExportJson: () -> String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val language = uiState.language
    val strings = UiStrings.get(language)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var selectedCategoryFilter by remember { mutableStateOf<PortalCategory?>(null) }
    var editingItem by remember { mutableStateOf<PortalUpdateItem?>(null) }
    var showCreateDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<PortalUpdateItem?>(null) }
    var showResetConfirm by remember { mutableStateOf(false) }
    var showChangePinDialog by remember { mutableStateOf(false) }

    val displayedItems = remember(uiState.allItems, selectedCategoryFilter) {
        if (selectedCategoryFilter == null) {
            uiState.allItems
        } else {
            uiState.allItems.filter { it.category == selectedCategoryFilter }
        }
    }

    if (showCreateDialog || editingItem != null) {
        AdminItemEditorDialog(
            existingItem = editingItem,
            language = language,
            onDismiss = {
                showCreateDialog = false
                editingItem = null
            },
            onSave = { saved ->
                onSaveItem(saved)
                showCreateDialog = false
                editingItem = null
                scope.launch {
                    snackbarHostState.showSnackbar(
                        if (language == AppLanguage.GUJARATI) "માહિતી Room DB માં સાચવવામાં આવી."
                        else "जानकारी Room DB में सेव हो गई।"
                    )
                }
            }
        )
    }

    if (itemToDelete != null) {
        val target = itemToDelete!!
        AlertDialog(
            onDismissRequest = { itemToDelete = null },
            title = { Text(strings.adminDeleteButton, fontWeight = FontWeight.Bold) },
            text = { Text(target.title.get(language)) },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteItem(target.id)
                        itemToDelete = null
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (language == AppLanguage.GUJARATI) "એન્ટ્રી કાઢી નાખવામાં આવી."
                                else "प्रविष्टि हटा दी गई।"
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                ) {
                    Text(strings.adminDeleteButton)
                }
            },
            dismissButton = {
                TextButton(onClick = { itemToDelete = null }) {
                    Text(strings.closeLabel)
                }
            }
        )
    }

    if (showResetConfirm) {
        AlertDialog(
            onDismissRequest = { showResetConfirm = false },
            title = { Text(strings.adminResetDemoButton, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    if (language == AppLanguage.GUJARATI) {
                        "શું તમે બધી સ્થાનિક એન્ટ્રીઝ મૂળ ડેમો સત્તાવાર પોર્ટલ યાદી પર રીસેટ કરવા માંગો છો?"
                    } else {
                        "क्या आप सभी स्थानीय प्रविष्टियों को मूल डेमो आधिकारिक पोर्टल सूची पर रीसेट करना चाहते हैं?"
                    }
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        onResetDemoCatalog()
                        showResetConfirm = false
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                if (language == AppLanguage.GUJARATI) "ડેમો કેટલોગ રીસેટ થયો."
                                else "डेमो कैटलॉग रीसेट हो गया।"
                            )
                        }
                    }
                ) {
                    Text(strings.adminResetDemoButton)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetConfirm = false }) {
                    Text(strings.closeLabel)
                }
            }
        )
    }

    if (showChangePinDialog) {
        var newPin by remember { mutableStateOf("") }
        var pinError by remember { mutableStateOf(false) }
        AlertDialog(
            onDismissRequest = { showChangePinDialog = false },
            title = { Text(strings.adminChangePinButton, fontWeight = FontWeight.Bold) },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = newPin,
                        onValueChange = {
                            newPin = it.filter { ch -> ch.isDigit() }.take(8)
                            pinError = false
                        },
                        label = {
                            Text(
                                if (language == AppLanguage.GUJARATI) "નવો PIN (ઓછામાં ઓછા 4 અંક)"
                                else "नया PIN (कम से कम 4 अंक)"
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                        singleLine = true,
                        isError = pinError
                    )
                    if (pinError) {
                        Text(
                            text = if (language == AppLanguage.GUJARATI) "ઓછામાં ઓછા 4 અંક દાખલ કરો."
                            else "कम से कम 4 अंक दर्ज करें।",
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (newPin.length >= 4) {
                            onUpdatePin(newPin)
                            showChangePinDialog = false
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    if (language == AppLanguage.GUJARATI) "નવો એડમિન PIN સાચવ્યો."
                                    else "नया एडमिन PIN सेव किया गया।"
                                )
                            }
                        } else {
                            pinError = true
                        }
                    }
                ) {
                    Text(strings.adminSaveButton)
                }
            },
            dismissButton = {
                TextButton(onClick = { showChangePinDialog = false }) {
                    Text(strings.closeLabel)
                }
            }
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = strings.adminPanelTitle,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = onBack,
                            modifier = Modifier.testTag("admin_back_button")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.cd_back_navigation)
                            )
                        }
                    },
                    actions = {
                        LanguageTogglePill(
                            currentLanguage = language,
                            onToggle = onToggleLanguage,
                            modifier = Modifier.padding(end = 10.dp)
                        )
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                )
                TricolorAccentBar()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showCreateDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.testTag("admin_add_fab")
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = strings.adminAddNewButton)
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("admin_items_list"),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Admin Utility Actions Bar (JSON Export, Reset Demo, Change PIN)
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = strings.adminPanelSubtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            item {
                                Button(
                                    onClick = { showCreateDialog = true },
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                                    modifier = Modifier.testTag("admin_add_button")
                                ) {
                                    Icon(Icons.Filled.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(strings.adminAddNewButton)
                                }
                            }
                            item {
                                OutlinedButton(
                                    onClick = {
                                        val json = onExportJson()
                                        val intent = Intent(Intent.ACTION_SEND).apply {
                                            type = "application/json"
                                            putExtra(Intent.EXTRA_SUBJECT, "gujarat_sahayak_catalog.json")
                                            putExtra(Intent.EXTRA_TEXT, json)
                                        }
                                        runCatching {
                                            context.startActivity(Intent.createChooser(intent, strings.adminExportJsonButton))
                                        }
                                    },
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                                    modifier = Modifier.testTag("admin_export_json_button")
                                ) {
                                    Icon(Icons.Filled.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(strings.adminExportJsonButton)
                                }
                            }
                            item {
                                OutlinedButton(
                                    onClick = { showResetConfirm = true },
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                                    modifier = Modifier.testTag("admin_reset_demo_button")
                                ) {
                                    Icon(Icons.Filled.RestartAlt, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(strings.adminResetDemoButton)
                                }
                            }
                            item {
                                OutlinedButton(
                                    onClick = { showChangePinDialog = true },
                                    contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
                                    modifier = Modifier.testTag("admin_change_pin_button")
                                ) {
                                    Icon(Icons.Filled.Key, contentDescription = null, modifier = Modifier.size(16.dp))
                                    Spacer(Modifier.width(4.dp))
                                    Text(strings.adminChangePinButton)
                                }
                            }
                        }
                    }
                }
            }

            // Category Filter Chips
            item {
                val editableCategories = PortalCategory.entries.filter { it != PortalCategory.FORM_HELP }
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    item {
                        FilterChip(
                            selected = selectedCategoryFilter == null,
                            onClick = { selectedCategoryFilter = null },
                            label = { Text("${strings.allLabel} (${uiState.allItems.size})") },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                    items(editableCategories) { cat ->
                        FilterChip(
                            selected = selectedCategoryFilter == cat,
                            onClick = { selectedCategoryFilter = cat },
                            label = { Text(UiStrings.categoryLabel(cat, language)) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = MaterialTheme.colorScheme.primary,
                                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                            )
                        )
                    }
                }
            }

            items(displayedItems, key = { it.id }) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("admin_item_card_${item.id}")
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(6.dp),
                                    color = MaterialTheme.colorScheme.primaryContainer
                                ) {
                                    Text(
                                        text = UiStrings.categoryLabel(item.category, language),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                                    )
                                }
                                if (item.isDemo) {
                                    DemoBadge(label = strings.demoBadgeLabel)
                                }
                            }
                            Row {
                                IconButton(
                                    onClick = { editingItem = item },
                                    modifier = Modifier.testTag("admin_edit_${item.id}")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Edit,
                                        contentDescription = strings.adminEditTitle,
                                        tint = MaterialTheme.colorScheme.primary
                                    )
                                }
                                IconButton(
                                    onClick = { itemToDelete = item },
                                    modifier = Modifier.testTag("admin_delete_${item.id}")
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = strings.adminDeleteButton,
                                        tint = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }

                        Text(
                            text = item.title.get(language),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "${item.officialSourceName} • ${item.publishedDate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            item { Spacer(modifier = Modifier.height(72.dp)) }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AdminItemEditorDialog(
    existingItem: PortalUpdateItem?,
    language: AppLanguage,
    onDismiss: () -> Unit,
    onSave: (PortalUpdateItem) -> Unit
) {
    val strings = UiStrings.get(language)
    val today = remember {
        SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
    }

    val editableCategories = remember {
        PortalCategory.entries.filter { it != PortalCategory.FORM_HELP }
    }

    var category by remember { mutableStateOf(existingItem?.category ?: PortalCategory.GOVT_JOBS) }
    var titleGu by remember { mutableStateOf(existingItem?.title?.gu.orEmpty()) }
    var titleHi by remember { mutableStateOf(existingItem?.title?.hi.orEmpty()) }
    var orgGu by remember { mutableStateOf(existingItem?.organization?.gu.orEmpty()) }
    var orgHi by remember { mutableStateOf(existingItem?.organization?.hi.orEmpty()) }
    var summaryGu by remember { mutableStateOf(existingItem?.shortSummary?.gu.orEmpty()) }
    var summaryHi by remember { mutableStateOf(existingItem?.shortSummary?.hi.orEmpty()) }
    var eligibilityGu by remember { mutableStateOf(existingItem?.eligibility?.gu.orEmpty()) }
    var eligibilityHi by remember { mutableStateOf(existingItem?.eligibility?.hi.orEmpty()) }
    var dateNoteGu by remember { mutableStateOf(existingItem?.importantDateNote?.gu.orEmpty()) }
    var officialSourceName by remember { mutableStateOf(existingItem?.officialSourceName.orEmpty()) }
    var officialUrl by remember { mutableStateOf(existingItem?.officialUrl ?: "https://") }
    var publishedDate by remember { mutableStateOf(existingItem?.publishedDate ?: today) }
    var tagsInput by remember { mutableStateOf(existingItem?.tags?.joinToString(", ").orEmpty()) }
    var isDemo by remember { mutableStateOf(existingItem?.isDemo ?: true) }
    var selectedScholarshipCats by remember {
        mutableStateOf(
            existingItem?.scholarshipCategories?.toSet() ?: setOf(ScholarshipCategory.GENERAL)
        )
    }
    var selectedAdmissionStream by remember {
        mutableStateOf(existingItem?.admissionStream ?: AdmissionStream.DEGREE_BA_BCOM_BSC)
    }
    var selectedSchemeScope by remember {
        mutableStateOf(existingItem?.schemeScope ?: SchemeScope.GUJARAT)
    }
    var validationError by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = if (existingItem == null) strings.adminCreateTitle else strings.adminEditTitle,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 460.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = if (language == AppLanguage.GUJARATI) "વિભાગ પસંદ કરો" else "श्रेणी चुनें",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    editableCategories.forEach { cat ->
                        FilterChip(
                            selected = category == cat,
                            onClick = { category = cat },
                            label = { Text(UiStrings.categoryLabel(cat, language)) }
                        )
                    }
                }

                // Conditional category sub-fields
                if (category == PortalCategory.SCHOLARSHIPS) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        ScholarshipCategory.entries.filter { it != ScholarshipCategory.ALL }.forEach { sc ->
                            val selected = sc in selectedScholarshipCats
                            FilterChip(
                                selected = selected,
                                onClick = {
                                    selectedScholarshipCats = if (selected) {
                                        selectedScholarshipCats - sc
                                    } else {
                                        selectedScholarshipCats + sc
                                    }
                                },
                                label = { Text(sc.code) }
                            )
                        }
                    }
                } else if (category == PortalCategory.COLLEGE_ADMISSIONS) {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        AdmissionStream.entries.filter { it != AdmissionStream.ALL }.forEach { st ->
                            FilterChip(
                                selected = selectedAdmissionStream == st,
                                onClick = { selectedAdmissionStream = st },
                                label = { Text(UiStrings.admissionStreamLabel(st, language)) }
                            )
                        }
                    }
                } else if (category == PortalCategory.GOVT_SCHEMES) {
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(
                            selected = selectedSchemeScope == SchemeScope.GUJARAT,
                            onClick = { selectedSchemeScope = SchemeScope.GUJARAT },
                            label = { Text(strings.gujaratSchemesLabel) }
                        )
                        FilterChip(
                            selected = selectedSchemeScope == SchemeScope.CENTRAL,
                            onClick = { selectedSchemeScope = SchemeScope.CENTRAL },
                            label = { Text(strings.centralSchemesLabel) }
                        )
                    }
                }

                OutlinedTextField(
                    value = titleGu,
                    onValueChange = { titleGu = it; validationError = null },
                    label = { Text("Title (ગુજરાતી) *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("admin_field_title_gu")
                )
                OutlinedTextField(
                    value = titleHi,
                    onValueChange = { titleHi = it; validationError = null },
                    label = { Text("Title (हिन्दी)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("admin_field_title_hi")
                )
                OutlinedTextField(
                    value = orgGu,
                    onValueChange = { orgGu = it },
                    label = { Text("Department / Organization (ગુજરાતી)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = orgHi,
                    onValueChange = { orgHi = it },
                    label = { Text("Department / Organization (हिन्दी)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = officialSourceName,
                    onValueChange = { officialSourceName = it },
                    label = { Text("Official Source (e.g., OJAS Gujarat) *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = officialUrl,
                    onValueChange = { officialUrl = it; validationError = null },
                    label = { Text("Official HTTPS URL *") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth().testTag("admin_field_url")
                )
                OutlinedTextField(
                    value = publishedDate,
                    onValueChange = { publishedDate = it },
                    label = { Text("Published Date (YYYY-MM-DD)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = summaryGu,
                    onValueChange = { summaryGu = it },
                    label = { Text("Summary (ગુજરાતી)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = summaryHi,
                    onValueChange = { summaryHi = it },
                    label = { Text("Summary (हिन्दी)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = eligibilityGu,
                    onValueChange = { eligibilityGu = it },
                    label = { Text("Eligibility (ગુજરાતી)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = eligibilityHi,
                    onValueChange = { eligibilityHi = it },
                    label = { Text("Eligibility (हिन्दी)") },
                    minLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = dateNoteGu,
                    onValueChange = { dateNoteGu = it },
                    label = { Text("Last Date / Official Notice Note") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                OutlinedTextField(
                    value = tagsInput,
                    onValueChange = { tagsInput = it },
                    label = { Text("Tags (comma separated)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = if (language == AppLanguage.GUJARATI) "ડેમો (DEMO) બેજ બતાવો"
                        else "डेमो (DEMO) बैज दिखाएं",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Switch(checked = isDemo, onCheckedChange = { isDemo = it })
                }

                if (validationError != null) {
                    Text(
                        text = validationError!!,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val cleanTitleGu = titleGu.trim().ifEmpty { titleHi.trim() }
                    val cleanTitleHi = titleHi.trim().ifEmpty { cleanTitleGu }
                    val cleanUrl = officialUrl.trim()
                    if (cleanTitleGu.isEmpty()) {
                        validationError = if (language == AppLanguage.GUJARATI) {
                            "શીર્ષક (Title) ફરજિયાત છે."
                        } else {
                            "शीर्षक (Title) आवश्यक है।"
                        }
                        return@Button
                    }
                    if (!cleanUrl.startsWith("https://") || cleanUrl.length < 12) {
                        validationError = if (language == AppLanguage.GUJARATI) {
                            "માન્ય અધિકૃત https:// વેબસાઇટ લિંક દાખલ કરો."
                        } else {
                            "वैध आधिकारिक https:// वेबसाइट लिंक दर्ज करें।"
                        }
                        return@Button
                    }

                    val cleanOrgGu = orgGu.trim().ifEmpty { orgHi.trim().ifEmpty { "ગુજરાત સરકાર / સત્તાવાર પોર્ટલ" } }
                    val cleanOrgHi = orgHi.trim().ifEmpty { cleanOrgGu }
                    val cleanSummaryGu = summaryGu.trim().ifEmpty { summaryHi.trim().ifEmpty { cleanTitleGu } }
                    val cleanSummaryHi = summaryHi.trim().ifEmpty { cleanSummaryGu }
                    val cleanEligGu = eligibilityGu.trim().ifEmpty {
                        eligibilityHi.trim().ifEmpty { "અધિકૃત પોર્ટલ પર લાયકાત ચકાસો." }
                    }
                    val cleanEligHi = eligibilityHi.trim().ifEmpty { cleanEligGu }
                    val cleanDateNote = dateNoteGu.trim().ifEmpty {
                        "અધિકૃત વેબસાઇટ પર તાજી તારીખ ચકાસો."
                    }
                    val parsedTags = tagsInput.split(",")
                        .map { it.trim() }
                        .filter { it.isNotEmpty() }
                        .ifEmpty { listOf(UiStrings.categoryLabel(category, AppLanguage.GUJARATI)) }

                    val item = PortalUpdateItem(
                        id = existingItem?.id ?: "custom_${UUID.randomUUID().toString().take(8)}",
                        category = category,
                        title = LocalizedText(cleanTitleGu, cleanTitleHi),
                        organization = LocalizedText(cleanOrgGu, cleanOrgHi),
                        shortSummary = LocalizedText(cleanSummaryGu, cleanSummaryHi),
                        eligibility = LocalizedText(cleanEligGu, cleanEligHi),
                        importantDateNote = LocalizedText(cleanDateNote, cleanDateNote),
                        publishedDate = publishedDate.trim().ifEmpty { today },
                        officialSourceName = officialSourceName.trim().ifEmpty { cleanUrl.removePrefix("https://").substringBefore("/") },
                        officialUrl = cleanUrl,
                        districtScope = existingItem?.districtScope ?: LocalizedText("સમગ્ર ગુજરાત", "संपूर्ण गुजरात"),
                        tags = parsedTags,
                        requiredDocuments = existingItem?.requiredDocuments ?: listOf(
                            LocalizedText("અધિકૃત જાહેરાત મુજબના દસ્તાવેજો", "आधिकारिक अधिसूचना के अनुसार दस्तावेज़")
                        ),
                        howToApplySteps = existingItem?.howToApplySteps ?: listOf(
                            LocalizedText("નીચે આપેલ અધિકૃત વેબસાઇટ ખોલી તાજી વિગતો ચકાસો.", "नीचे दी गई आधिकारिक वेबसाइट खोलकर नवीनतम जानकारी जांचें।")
                        ),
                        scholarshipCategories = if (category == PortalCategory.SCHOLARSHIPS) {
                            selectedScholarshipCats.toList().ifEmpty { listOf(ScholarshipCategory.GENERAL) }
                        } else emptyList(),
                        admissionStream = if (category == PortalCategory.COLLEGE_ADMISSIONS) selectedAdmissionStream else null,
                        schemeScope = if (category == PortalCategory.GOVT_SCHEMES) selectedSchemeScope else null,
                        isDemo = isDemo
                    )
                    onSave(item)
                },
                modifier = Modifier.testTag("admin_save_item_button")
            ) {
                Text(strings.adminSaveButton)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(strings.closeLabel)
            }
        }
    )
}

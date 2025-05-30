plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.sample.dt"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sample.dt"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    flavorDimensions += "verify"

    productFlavors {
        create("dttStaging") {
            dimension = "verify"

            // Base URLs
            buildConfigField("String", "API_VERSION", "\"\"")
            buildConfigField("String", "BASE_URL", "\"https://uaeid-stg.digitaltrusttech.com:4004/\"")
            buildConfigField("String", "BASE_URL_IDP", "\"https://uaeid-stg.digitaltrusttech.com/uaeid-idp/\"")
            buildConfigField("String", "BASE_URL_CONFIG", "\"https://uaeid-stg.digitaltrusttech.com/uaeid-OnBoarding/\"")
            buildConfigField("String", "BASE_URL_MDL", "\"https://mytrust.digitaltrusttech.com/dt-stg-mdoc/\"")
            buildConfigField("String", "BASE_URL_LOG", "\"https://uaeid.digitaltrusttech.com/Wallet-Transaction-log/wallettransaction/api/\"")
            buildConfigField("String", "BASE_URL_PDF_DOCUMENT", "\"https://uaeid.digitaltrusttech.com/mytrust-SignatureServiceAgent/SignatureServiceAgent/\"")
            buildConfigField("String", "BASE_URL_VAULT", "\"https://uaeid-stg.digitaltrusttech.com/uaeid-admin-portal/\"")
            buildConfigField("String", "BASE_URL_VC", "\"https://uaeid-stg.digitaltrusttech.com/credential-exchange/\"")
            buildConfigField("String", "BASE_URL_FACE", "\"https://staging.digitaltrusttech.com/face-matching/\"")
            buildConfigField("String", "BASE_URL_KYC", "\"https://uaeid-stg.digitaltrusttech.com:4044/\"")
            buildConfigField("String", "BASE_URL_KYC_SUBMIT", "\"https://uaeid-stg.digitaltrusttech.com/\"")

            resValue("string", "app_name", "UAEID eVerify DTT")
        }

        create("nitaStaging") {
            dimension = "verify"

            // Base URLs
            buildConfigField("String", "API_VERSION", "\"\"")
            buildConfigField("String", "BASE_URL", "\"https://stgsign.ugpass.go.ug/\"")
            buildConfigField("String", "BASE_URL_IDP", "\"https://stgapi.ugpass.go.ug/\"")
            buildConfigField("String", "BASE_URL_CONFIG", "\"https://stgapi.ugpass.go.ug/onboarding/\"")
            buildConfigField("String", "BASE_URL_MDL", "\"https://stgapi.ugpass.go.ug/mdoc-nita-stg/\"")
            buildConfigField("String", "BASE_URL_LOG", "\"https://stgapi.ugpass.go.ug/Wallet-Transaction-log/\"")
            buildConfigField("String", "BASE_URL_PDF_DOCUMENT", "\"https://stgapi.ugpass.go.ug/signing-service/SignatureWebService/\"")
            buildConfigField("String", "BASE_URL_VAULT", "\"https://adminportal.digitaltrusttech.com/admin-portal/\"")
            buildConfigField("String", "BASE_URL_VC", "\"https://mytrust.digitaltrusttech.com/credential-exchange/\"")
            buildConfigField("String", "BASE_URL_FACE", "\"https://staging.digitaltrusttech.com/face-matching/\"")
            buildConfigField("String", "BASE_URL_KYC", "\"https://uaeid-stg.digitaltrusttech.com:4044/\"")
            buildConfigField("String", "BASE_URL_KYC_SUBMIT", "\"https://uaeid-stg.digitaltrusttech.com:4044/\"")

            resValue("string", "app_name", "UAEID eVerify STG")
        }

        create("production") {
            dimension = "verify"

            // Base URLs
            buildConfigField("String", "API_VERSION", "\"/v1\"")
            buildConfigField("String", "BASE_URL", "\"https://stgsign.ugpass.go.ug/\"")
            buildConfigField("String", "BASE_URL_IDP", "\"https://stgapi.ugpass.go.ug/idp/\"")
            buildConfigField("String", "BASE_URL_CONFIG", "\"\"")
            buildConfigField("String", "BASE_URL_MDL", "\"\"")
            buildConfigField("String", "BASE_URL_LOG", "\"\"")
            buildConfigField("String", "BASE_URL_PDF_DOCUMENT", "\"\"")
            buildConfigField("String", "BASE_URL_VAULT", "\"\"")
            buildConfigField("String", "BASE_URL_VC", "\"\"")
            buildConfigField("String", "BASE_URL_FACE", "\"\"")
            buildConfigField("String", "BASE_URL_KYC", "\"\"")
            buildConfigField("String", "BASE_URL_KYC_SUBMIT", "\"\"")

            resValue("string", "app_name", "UAEID eVerify")
        }
    }

    buildTypes {
        getByName("debug") {
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    applicationVariants.all {
        val variant = this
        variant.outputs.all {
            val outputImpl = this as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            val appName = variant.productFlavors.firstOrNull()?.resValues?.get("app_name")?.value ?: "UAEID Verify"
            val versionName = variant.versionName
            val flavorName = variant.flavorName
            val buildTypeName = variant.buildType.name
            val outputFileName =
                "$appName-$versionName-$flavorName-$buildTypeName.${outputImpl.outputFile.extension}"
            outputImpl.outputFileName = outputFileName
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
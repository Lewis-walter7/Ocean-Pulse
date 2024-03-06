package com.licoding.oceanpulse.domain.utils

import com.licoding.oceanpulse.domain.models.Blog
import com.licoding.oceanpulse.domain.response.BlogResponse


fun BlogResponse.toBlog() : Blog {
    return Blog(
        url = url,
        title = title,
        image = image,
        description = description
    )
}
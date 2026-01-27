package com.ecm.modules.deal.domain;

public enum DealStatus {
    DRAFT,      // created but not live
    ACTIVE,     // users can use it
    EXPIRED,    // time over
    DISABLED    // manually turned off
}


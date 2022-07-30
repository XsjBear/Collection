package org.example.io.Thread.DataTransfer;

import org.springframework.beans.factory.annotation.Autowired;

class Demo07Test {

    @Autowired
    private Demo07 demo07;

    @org.junit.jupiter.api.Test
    void exChange() {
        demo07.exChange();
    }
}
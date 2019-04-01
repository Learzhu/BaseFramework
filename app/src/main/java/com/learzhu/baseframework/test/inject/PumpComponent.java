package com.learzhu.baseframework.test.inject;

import dagger.Component;

@Component
public interface PumpComponent {
    Thermosiphon getPump();
}
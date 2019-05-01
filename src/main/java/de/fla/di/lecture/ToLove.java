package de.fla.di.lecture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToLove implements NeedsSomeBody {
    public void toLove() {
        LOGGER.info("be loved");
    }
}
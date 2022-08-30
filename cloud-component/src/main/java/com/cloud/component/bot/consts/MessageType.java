package com.cloud.component.bot.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
  TEXT(0),
  IMAGE(1),
  URL_LINK(2),
  FILE(3),
  MINI_PROGRAM(4),
  VIDEO(5),
  CHANNEL(7),
  VOICE(8),
  EMOTICON(9),
  LOCATION(10);

  private final int code;
}
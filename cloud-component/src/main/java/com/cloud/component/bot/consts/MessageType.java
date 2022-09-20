package com.cloud.component.bot.consts;

import com.cloud.component.bot.message.ImageMessage;
import com.cloud.component.bot.message.LinkMessage;
import com.cloud.component.bot.message.Message;
import com.cloud.component.bot.message.TextMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {
  TEXT(0, TextMessage.class),
  IMAGE(1, ImageMessage.class),
  URL_LINK(2, LinkMessage.class),
//  FILE(3),
//  MINI_PROGRAM(4),
//  VIDEO(5),
//  CHANNEL(7),
//  VOICE(8),
//  EMOTICON(9),
//  LOCATION(10),
  ;

  private final int code;

  private final Class<? extends Message> msgClass;
}
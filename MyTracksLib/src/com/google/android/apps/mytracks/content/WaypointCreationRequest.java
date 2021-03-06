/*
 * Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.android.apps.mytracks.content;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * A request for the service to create a waypoint at the current location.
 *
 * @author Sandor Dornbush
 */
public class WaypointCreationRequest implements Parcelable {

  public static enum WaypointType {
    MARKER,
    STATISTICS;
  }
  
  private WaypointType type;
  private String name;
  private String description;
  private String iconUrl;

  public final static WaypointCreationRequest DEFAULT_MARKER =
      new WaypointCreationRequest(WaypointType.MARKER);
  public final static WaypointCreationRequest DEFAULT_STATISTICS =
      new WaypointCreationRequest(WaypointType.STATISTICS);
  
  private WaypointCreationRequest(WaypointType type) {
    this.type = type;
  }

  public WaypointCreationRequest(WaypointType type, String name,
      String description, String iconUrl) {
    this.type = type;
    this.name = name;
    this.description = description;
    this.iconUrl = iconUrl;
  }

  public static class Creator implements Parcelable.Creator<WaypointCreationRequest> {

    @Override
    public WaypointCreationRequest createFromParcel(Parcel source) {
      int i = source.readInt();
      if (i > WaypointType.values().length) {
        throw new IllegalArgumentException("Could not find waypoint type: " + i);
      }
      WaypointCreationRequest request = new WaypointCreationRequest(WaypointType.values()[i]);
      request.description = source.readString();
      request.iconUrl = source.readString();
      request.name = source.readString();
      return request;
    }

    public WaypointCreationRequest[] newArray(int size) {
      return new WaypointCreationRequest[size];
    }
  }

  public static final Creator CREATOR = new Creator();
  
  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel parcel, int arg1) {
    parcel.writeInt(type.ordinal());
    parcel.writeString(description);
    parcel.writeString(iconUrl);
    parcel.writeString(name);
  }
  
  public WaypointType getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getIconUrl() {
    return iconUrl;
  }
}
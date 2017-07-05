// author: Angel Olle Blazquez

#include "com_olleb_bad_core_CoreServlet.h"

void _crash() {
        int *a  = NULL;
        *a += 1;
}

JNIEXPORT void JNICALL Java_com_olleb_bad_core_CoreServlet_core
  (JNIEnv *env, jobject o) {
        _crash();
}

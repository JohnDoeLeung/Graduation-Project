<template>
  <h2 class="mb-3 text-2xl font-bold text-center xl:text-3xl enter-x xl:text-center">
    {{ getFormTitle }}
  </h2>
</template>
<script lang="ts" name="LoginFormTitle" setup>
import { LoginStateEnum, useLoginState } from './useLogin'

const { t } = useI18n()

const { getLoginState } = useLoginState()

const getFormTitle = computed(() => {
  const titleObj = {
    [LoginStateEnum.LOGIN]: t('sys.login.signInFormTitle'),
  }
  return titleObj[unref(getLoginState)]
})
</script>

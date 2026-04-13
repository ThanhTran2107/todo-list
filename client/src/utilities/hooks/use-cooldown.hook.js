import { useEffect, useState, useCallback } from 'react';
import { getCooldown, setCooldown } from '@/utilities/services/storage.service';
import { RESET_PASSWORD_CONFIG } from '@/utilities/constants';

export const useCooldown = (email) => {
  const [cooldown, setCooldownState] = useState(0);

  // Sync cooldown when email changes
  useEffect(() => {
    if (!email) return setCooldownState(0);
    
    setCooldownState(getCooldown(email));
  }, [email]);

  // Countdown timer
  useEffect(() => {
    if (cooldown === 0) return;

    const timer = setTimeout(() => {
      setCooldownState((prev) => prev - 1);
    }, 1000);

    return () => clearTimeout(timer);
  }, [cooldown]);

  const resetCooldown = useCallback(() => {
    const seconds = RESET_PASSWORD_CONFIG.COOL_DOWN_SECOND;
    
    setCooldown(email, seconds);
    setCooldownState(seconds);
  }, [email]);

  return {
    cooldown,
    isOnCooldown: cooldown > 0,
    resetCooldown,
  };
};

